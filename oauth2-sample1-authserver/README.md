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




