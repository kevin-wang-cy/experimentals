```get user info by token

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