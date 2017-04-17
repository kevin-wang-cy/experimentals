```bash

curl -vv -u "svc-account-3:svc-account-3-secret" -X POST "http://localhost:9800/auth/oauth/token" \
         -d grant_type=password \
         -d username=user \
         -d password=none \
         | jq .

{
  "access_token": "0cbe4708-3d8e-43c8-9904-2d165a54875e",
  "token_type": "bearer",
  "refresh_token": "eb745ddb-6cdf-4bc4-adac-f218f299ee81",
  "expires_in": 299,
  "scope": "resource-server-read resource-server-write"
}


curl -H "Authorization: Bearer 0cbe4708-3d8e-43c8-9904-2d165a54875e" -v localhost:8800/rs/user | jq .

{
  "authorities": [
    {
      "authority": "ROLE_ACTUATOR"
    },
    {
      "authority": "ROLE_USER"
    }
  ],
  "details": {
    "remoteAddress": "0:0:0:0:0:0:0:1",
    "sessionId": null,
    "tokenValue": "0cbe4708-3d8e-43c8-9904-2d165a54875e",
    "tokenType": "Bearer",
    "decodedDetails": null
  },
  "authenticated": true,
  "userAuthentication": {
    "authorities": [
      {
        "authority": "ROLE_ACTUATOR"
      },
      {
        "authority": "ROLE_USER"
      }
    ],
    "details": {
      "authorities": [
        {
          "authority": "ROLE_ACTUATOR"
        },
        {
          "authority": "ROLE_USER"
        }
      ],
      "details": {
        "remoteAddress": "127.0.0.1",
        "sessionId": null,
        "tokenValue": "0cbe4708-3d8e-43c8-9904-2d165a54875e",
        "tokenType": "Bearer",
        "decodedDetails": null
      },
      "authenticated": true,
      "userAuthentication": {
        "authorities": [
          {
            "authority": "ROLE_ACTUATOR"
          },
          {
            "authority": "ROLE_USER"
          }
        ],
        "details": {
          "grant_type": "password",
          "username": "user"
        },
        "authenticated": true,
        "principal": {
          "password": null,
          "username": "user",
          "authorities": [
            {
              "authority": "ROLE_ACTUATOR"
            },
            {
              "authority": "ROLE_USER"
            }
          ],
          "accountNonExpired": true,
          "accountNonLocked": true,
          "credentialsNonExpired": true,
          "enabled": true
        },
        "credentials": null,
        "name": "user"
      },
      "principal": {
        "password": null,
        "username": "user",
        "authorities": [
          {
            "authority": "ROLE_ACTUATOR"
          },
          {
            "authority": "ROLE_USER"
          }
        ],
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true,
        "enabled": true
      },
      "oauth2Request": {
        "clientId": "svc-account-3",
        "scope": [
          "resource-server-read",
          "resource-server-write"
        ],
        "requestParameters": {
          "grant_type": "password",
          "username": "user"
        },
        "resourceIds": [],
        "authorities": [],
        "approved": true,
        "refresh": false,
        "redirectUri": null,
        "responseTypes": [],
        "extensions": {},
        "grantType": "password",
        "refreshTokenRequest": null
      },
      "clientOnly": false,
      "credentials": "",
      "name": "user"
    },
    "authenticated": true,
    "principal": "user",
    "credentials": "N/A",
    "name": "user"
  },
  "principal": "user",
  "clientOnly": false,
  "credentials": "",
  "oauth2Request": {
    "clientId": null,
    "scope": [],
    "requestParameters": {},
    "resourceIds": [],
    "authorities": [],
    "approved": true,
    "refresh": false,
    "redirectUri": null,
    "responseTypes": [],
    "extensions": {},
    "refreshTokenRequest": null,
    "grantType": null
  },
  "name": "user"
}


```
```bash
curl -vv -u "svc-account-3:svc-account-3-secret" -X POST "http://localhost:9800/auth/oauth/token" -d grant_type=client_credentials | jq .

{
  "access_token": "0b92e94e-166e-4e83-9d2b-1e787d4a55af",
  "token_type": "bearer",
  "expires_in": 293,
  "scope": "resource-server-read resource-server-write"
}

curl -H "Authorization: Bearer 0b92e94e-166e-4e83-9d2b-1e787d4a55af" -v localhost:8800/rs/user | jq .

{
  "authorities": [],
  "details": {
    "remoteAddress": "0:0:0:0:0:0:0:1",
    "sessionId": null,
    "tokenValue": "0b92e94e-166e-4e83-9d2b-1e787d4a55af",
    "tokenType": "Bearer",
    "decodedDetails": null
  },
  "authenticated": true,
  "userAuthentication": {
    "authorities": [],
    "details": {
      "authorities": [],
      "details": {
        "remoteAddress": "127.0.0.1",
        "sessionId": null,
        "tokenValue": "0b92e94e-166e-4e83-9d2b-1e787d4a55af",
        "tokenType": "Bearer",
        "decodedDetails": null
      },
      "authenticated": true,
      "userAuthentication": null,
      "principal": "svc-account-3",
      "oauth2Request": {
        "clientId": "svc-account-3",
        "scope": [
          "resource-server-read",
          "resource-server-write"
        ],
        "requestParameters": {
          "grant_type": "client_credentials"
        },
        "resourceIds": [],
        "authorities": [],
        "approved": true,
        "refresh": false,
        "redirectUri": null,
        "responseTypes": [],
        "extensions": {},
        "grantType": "client_credentials",
        "refreshTokenRequest": null
      },
      "clientOnly": true,
      "credentials": "",
      "name": "svc-account-3"
    },
    "authenticated": true,
    "principal": "svc-account-3",
    "credentials": "N/A",
    "name": "svc-account-3"
  },
  "principal": "svc-account-3",
  "clientOnly": false,
  "credentials": "",
  "oauth2Request": {
    "clientId": null,
    "scope": [],
    "requestParameters": {},
    "resourceIds": [],
    "authorities": [],
    "approved": true,
    "refresh": false,
    "redirectUri": null,
    "responseTypes": [],
    "extensions": {},
    "refreshTokenRequest": null,
    "grantType": null
  },
  "name": "svc-account-3"
}

```

```bash
# Test Access Control - svc-account-3 doesn't have the ROLE_RS_READ permission
curl -vv -u "svc-account-3:svc-account-3-secret" -X POST "http://localhost:9800/auth/oauth/token" -d grant_type=client_credentials | jq .

{
  "access_token": "37d2e42d-d0fa-48ed-9365-b75ec2e8787f",
  "token_type": "bearer",
  "expires_in": 299,
  "scope": "resource-server-read resource-server-write"
}

curl -H "Authorization: Bearer 37d2e42d-d0fa-48ed-9365-b75ec2e8787f" -v localhost:8800/rs/ | jq .

{
  "error": "access_denied",
  "error_description": "Access is denied"
}


# Test Access Control - svc-account-2 defines resource ids but not have "oauth2_resource" permission
curl -vv -u "svc-account-2:svc-account-2-secret" -X POST "http://localhost:9800/auth/oauth/token" -d grant_type=client_credentials | jq .

{
  "access_token": "88240c75-d51f-4093-9cf4-9734ae512ff9",
  "token_type": "bearer",
  "expires_in": 299,
  "scope": "scope-1 scope-2"
}

curl -H "Authorization: Bearer 88240c75-d51f-4093-9cf4-9734ae512ff9" -v localhost:8800/rs/ | jq .

{
  "error": "invalid_token",
  "error_description": "88240c75-d51f-4093-9cf4-9734ae512ff9"
}

# Test Access Control - svc-account-4 defines resource ids with "oauth2_resource" in it

curl -vv -u "svc-account-4:svc-account-4-secret" -X POST "http://localhost:9800/auth/oauth/token" -d grant_type=client_credentials | jq .

{
  "access_token": "5ab85813-9ab8-4e04-9b62-f36fb5967362",
  "token_type": "bearer",
  "expires_in": 299,
  "scope": "scope-1 scope-2"
}

curl -H "Authorization: Bearer 5ab85813-9ab8-4e04-9b62-f36fb5967362" -v localhost:8800/rs/ | jq .
 
{
  "message": "Hello world!"
}

# Test #oauth2.hasScope access, access denied due to no scope defined in svc-account-4 client
curl -H "Authorization: Bearer 5ab85813-9ab8-4e04-9b62-f36fb5967362" -v localhost:8800/rs/user | jq .
{
  "error": "access_denied",
  "error_description": "Access is denied"
}


## Test #oauth2.hasScope access, still access denied on svc-account-3 client is due to bug: 
curl -vv -u "svc-account-3:svc-account-3-secret" -X POST "http://localhost:9800/auth/oauth/token" -d grant_type=client_credentials | jq .

{
  "access_token": "a2f94c98-9e0d-4d9b-ac38-2c95b53ff990",
  "token_type": "bearer",
  "expires_in": 264,
  "scope": "resource-server-read resource-server-write"
}

curl -H "Authorization: Bearer a2f94c98-9e0d-4d9b-ac38-2c95b53ff990" -v localhost:8800/rs/user | jq .

{
  "error": "access_denied",
  "error_description": "Access is denied"
}


```