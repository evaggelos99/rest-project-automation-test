package com.example.demo.auth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/usersauth")
@RestController
public class UserAuthController {

	private static final Logger log = LoggerFactory.getLogger(UserAuthController.class);

	private final String authServerUrl = "http://localhost:8080/auth";
	private final String realm = "tutorialsbuddy-example";
	private final String clientId = "tutorialsbuddy-app";
	private final String role = "student";
	// Get client secret from the Keycloak admin console (in the credential tab)
	private final String clientSecret = "7b768120-ef4e-4100-8c99-85bb9d4dc5c3";

	@PostMapping(path = "/create")
	public ResponseEntity<?> createUser(@RequestBody final UserDTO userDTO) {

		final Keycloak keycloak = KeycloakBuilder.builder().serverUrl(this.authServerUrl)
				.grantType(OAuth2Constants.PASSWORD).realm("master").clientId("admin-cli").username("admin")
				.password("Testing123").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
				.build();

		keycloak.tokenManager().getAccessToken();

		final UserRepresentation user = new UserRepresentation();
		user.setEnabled(true);
		user.setUsername(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstname());
		user.setLastName(userDTO.getLastname());
		user.setEmail(userDTO.getEmail());

		// Get realm
		final RealmResource realmResource = keycloak.realm(this.realm);
		final UsersResource usersRessource = realmResource.users();

		final Response response = usersRessource.create(user);

		userDTO.setStatusCode(response.getStatus());
		userDTO.setStatus(response.getStatusInfo().toString());

		if (response.getStatus() == 201) {

			final String userId = CreatedResponseUtil.getCreatedId(response);

			log.info("Created userId {}", userId);

			// create password credential
			final CredentialRepresentation passwordCred = new CredentialRepresentation();
			passwordCred.setTemporary(false);
			passwordCred.setType(CredentialRepresentation.PASSWORD);
			passwordCred.setValue(userDTO.getPassword());

			final UserResource userResource = usersRessource.get(userId);

			// Set password credential
			userResource.resetPassword(passwordCred);

			// Get realm role student
			final RoleRepresentation realmRoleUser = realmResource.roles().get(this.role).toRepresentation();

			// Assign realm role student to user
			userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser));
		}
		return ResponseEntity.ok(userDTO);
	}

	@PostMapping(path = "/signin")
	public ResponseEntity<?> signin(@RequestBody final UserDTO userDTO) {

		final Map<String, Object> clientCredentials = new HashMap<>();
		clientCredentials.put("secret", this.clientSecret);
		clientCredentials.put("grant_type", "password");

		final Configuration configuration = new Configuration(this.authServerUrl, this.realm, this.clientId,
				clientCredentials, null);
		final AuthzClient authzClient = AuthzClient.create(configuration);

		final AccessTokenResponse response = authzClient.obtainAccessToken(userDTO.getEmail(), userDTO.getPassword());

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/unprotected-data")
	public String getName() {
		return "Hello, this api is not protected.";
	}

	@GetMapping(value = "/protected-data")
	public String getEmail() {
		return "Hello, this api is protected.";
	}

}