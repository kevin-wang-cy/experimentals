```client_credentials grant_type

curl -vv -u "svc-account-1:svc-account-1-secret" -X POST "http://localhost:9800/auth/oauth/token" \
        -d grant_type=client_credentials 

{
  "access_token": "0bdbdc8a-3fe8-4e7c-9304-0ea797ac0810",
  "token_type": "bearer",
  "expires_in": 281,
  "scope": "resource-server-read resource-server-write"
}

```

```refresh_token

curl -vv -u "svc-account-3:svc-account-3-secret" -X POST "http://localhost:9800/auth/oauth/token" \
         -d grant_type=password \
         -d username=user \
         -d password=none | jq .


{
  "access_token": "b714cda8-aec2-4632-9eb9-bca8660fc11f",
  "token_type": "bearer",
  "refresh_token": "a9ba9db5-aaad-414c-b404-1e8645310453",
  "expires_in": 265,
  "scope": "resource-server-read resource-server-write"
}



curl -vv -u "svc-account-3:svc-account-3-secret" -X POST "http://localhost:9800/auth/oauth/token" \
         -d grant_type=refresh_token \
         -d refresh_token=a9ba9db5-aaad-414c-b404-1e8645310453 \
         | jq .
         
{
  "access_token": "359a697c-739d-4cb6-95bf-9fe74a847205",
  "token_type": "bearer",
  "refresh_token": "a9ba9db5-aaad-414c-b404-1e8645310453",
  "expires_in": 299,
  "scope": "resource-server-read resource-server-write"
}
         
```

```password grant_type

curl -vv -u "svc-account-2:svc-account-2-secret" -X POST "http://localhost:9800/auth/oauth/token" \
         -d grant_type=password \
         -d username=user \
         -d password=none

{
    "access_token": "dcb1bddd-3f8c-4251-b41c-ec7b2ba7881d", 
    "expires_in": 59, 
    "refresh_token": "3e3a28fa-5cef-471f-bd7f-e0170a69fc20", 
    "scope": "scope-1 scope-2", 
    "token_type": "bearer"
}

```

```authorization code

http://localhost:9800/auth/oauth/authorize?response_type=code&client_id=svc-account-2&scope=scope-1+scope-2&state=12345&redirect_uri=http%3A%2F%2Fredirect-uri-2

http://redirect-uri-2/?code=rZQgX5&state=12345

curl -vv -u "svc-account-2:svc-account-2-secret" -X POST "http://localhost:9800/auth/oauth/token" \
         -d grant_type=authorization_code \
         -d code=rZQgX5 \
         -d state=12345 \
         -d redirect_uri=http://redirect-uri-2
         
{
  "access_token": "9ba25aab-7781-4cd8-8270-6a91bb0a25d3",
  "token_type": "bearer",
  "refresh_token": "01455a22-8c58-455f-bee4-664c667ae7fa",
  "expires_in": 59,
  "scope": "scope-1 scope-2"
}

```

```get user info by user autheticationed access token

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


curl -H "Authorization: Bearer 0cbe4708-3d8e-43c8-9904-2d165a54875e" -v localhost:9800/auth/me | jq .

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
}

# get user by client only authentacated access token 
curl -vv -u "svc-account-3:svc-account-3-secret" -X POST "http://localhost:9800/auth/oauth/token"         -d grant_type=client_credentials  | jq .

{
  "access_token": "2901a651-ffb6-425a-a719-5d5ae739c157",
  "token_type": "bearer",
  "expires_in": 293,
  "scope": "resource-server-read resource-server-write"
}

curl -H "Authorization: Bearer 2901a651-ffb6-425a-a719-5d5ae739c157" -v localhost:9800/auth/me | jq .

{
  "authorities": [
    {
      "authority": "ROLE_RS_WRITE"
    }
  ],
  "details": {
    "remoteAddress": "0:0:0:0:0:0:0:1",
    "sessionId": null,
    "tokenValue": "2901a651-ffb6-425a-a719-5d5ae739c157",
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
    "authorities": [
      {
        "authority": "ROLE_RS_WRITE"
      }
    ],
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
}


```

```bash
# add keystore for jwt

keytool -genkeypair -alias jwt-default-test -keyalg RSA -dname "CN=jwt-default-test, OU=Auth Server, O=upbchain, C=CN" -keypass mySecretKey -keystore jwt.jks -storepass mySecretKey  

curl -vv -u "svc-account-3:svc-account-3-secret" -X POST "http://localhost:9800/auth/oauth/token"         -d grant_type=client_credentials  | jq .

{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZXNvdXJjZS1zZXJ2ZXItcmVhZCIsInJlc291cmNlLXNlcnZlci13cml0ZSJdLCJleHAiOjE0OTI0NDIxOTIsImF1dGhvcml0aWVzIjpbIlJPTEVfUlNfV1JJVEUiXSwianRpIjoiOWE1YTAxN2YtMWZkZS00MDU0LWFhMmQtYzg4OWNkZjdhMmJjIiwiY2xpZW50X2lkIjoic3ZjLWFjY291bnQtMyJ9.TN7_O-l4_Ryd0aS8D8HjPkyXoXNtEBt398b5GJ5s33JscZpovMhYfQLmWV6FwGZpRpgBjH1tnpc1RghgABa0esr0Zf9VwYUoyLese_afW-6mO74LBSE9XV02lD0FJj2DE67AABlFCvF7ge5h0XyNJ1qSl5q-05BAlIu_NmVrW2iL3kc2ZKjSIz3nuP0jeoSriXrtRrONeD-1yZdwX9h5C-DqXqMCF6v7KsB084T0O5ds2tReuhbuj0XP-eI-7nX3EzsS59SaZ4OkBthGJRRUYmXsKuhtubDnwlBi47OePN72z8VwgNcbWna657D5LHyoHNo03a8G97KZcgrk9bPM0A",
  "token_type": "bearer",
  "expires_in": 299,
  "scope": "resource-server-read resource-server-write",
  "jti": "9a5a017f-1fde-4054-aa2d-c889cdf7a2bc"
}


curl -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZXNvdXJjZS1zZXJ2ZXItcmVhZCIsInJlc291cmNlLXNlcnZlci13cml0ZSJdLCJleHAiOjE0OTI0NDIxOTIsImF1dGhvcml0aWVzIjpbIlJPTEVfUlNfV1JJVEUiXSwianRpIjoiOWE1YTAxN2YtMWZkZS00MDU0LWFhMmQtYzg4OWNkZjdhMmJjIiwiY2xpZW50X2lkIjoic3ZjLWFjY291bnQtMyJ9.TN7_O-l4_Ryd0aS8D8HjPkyXoXNtEBt398b5GJ5s33JscZpovMhYfQLmWV6FwGZpRpgBjH1tnpc1RghgABa0esr0Zf9VwYUoyLese_afW-6mO74LBSE9XV02lD0FJj2DE67AABlFCvF7ge5h0XyNJ1qSl5q-05BAlIu_NmVrW2iL3kc2ZKjSIz3nuP0jeoSriXrtRrONeD-1yZdwX9h5C-DqXqMCF6v7KsB084T0O5ds2tReuhbuj0XP-eI-7nX3EzsS59SaZ4OkBthGJRRUYmXsKuhtubDnwlBi47OePN72z8VwgNcbWna657D5LHyoHNo03a8G97KZcgrk9bPM0A" -v localhost:9800/auth/me | jq .

{
  "authorities": [
    {
      "authority": "ROLE_RS_WRITE"
    }
  ],
  "details": {
    "remoteAddress": "0:0:0:0:0:0:0:1",
    "sessionId": null,
    "tokenValue": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZXNvdXJjZS1zZXJ2ZXItcmVhZCIsInJlc291cmNlLXNlcnZlci13cml0ZSJdLCJleHAiOjE0OTI0NDIxOTIsImF1dGhvcml0aWVzIjpbIlJPTEVfUlNfV1JJVEUiXSwianRpIjoiOWE1YTAxN2YtMWZkZS00MDU0LWFhMmQtYzg4OWNkZjdhMmJjIiwiY2xpZW50X2lkIjoic3ZjLWFjY291bnQtMyJ9.TN7_O-l4_Ryd0aS8D8HjPkyXoXNtEBt398b5GJ5s33JscZpovMhYfQLmWV6FwGZpRpgBjH1tnpc1RghgABa0esr0Zf9VwYUoyLese_afW-6mO74LBSE9XV02lD0FJj2DE67AABlFCvF7ge5h0XyNJ1qSl5q-05BAlIu_NmVrW2iL3kc2ZKjSIz3nuP0jeoSriXrtRrONeD-1yZdwX9h5C-DqXqMCF6v7KsB084T0O5ds2tReuhbuj0XP-eI-7nX3EzsS59SaZ4OkBthGJRRUYmXsKuhtubDnwlBi47OePN72z8VwgNcbWna657D5LHyoHNo03a8G97KZcgrk9bPM0A",
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
      "client_id": "svc-account-3"
    },
    "resourceIds": [],
    "authorities": [
      {
        "authority": "ROLE_RS_WRITE"
      }
    ],
    "approved": true,
    "refresh": false,
    "redirectUri": null,
    "responseTypes": [],
    "extensions": {},
    "refreshTokenRequest": null,
    "grantType": null
  },
  "clientOnly": true,
  "credentials": "",
  "name": "svc-account-3"
}


```


