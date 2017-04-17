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