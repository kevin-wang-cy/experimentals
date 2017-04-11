#!/bin/bash
set -e

ENCRYPT_PASSCODE="upbchain"

# Wallet DB
MYSQL_HOST="139.196.123.181"
MYSQL_PORT="3389"
MYSQL_DATABASE="poicoin"
MYSQL_USER="wallet"
MYSQL_PASSWORD="Qv6lamjmw9fUj8bnCUMXxtXOOWLvG0mn"

#Member System Info
MEM_HOST="127.0.0.1"

# XUP INFO
XUP_HOST="127.0.0.1"
XUP_DR_URL="http://127.0.0.1:8080/api/echo"
XUP_DR_USER="admin"
XUP_DR_PASSWORD="IMpSTiJKjhjhXQi7UVkk+w6xsb4UHpoF"

# Wallet Info
ALIAS="unxcoin-api"
USER_PASSWORD="ToaZZY0rLg/Tq2s+rEj5IGEhpfTo/Zxz"

WALLET_API_VERSION="0.15"
WALLET_API_PORT="8080"
WALLET_API_HOST=`/sbin/ifconfig eth0|grep inet|head -1|sed 's/\:/ /'|awk '{print $3}'`
WALLET_DOCKER_HOST=`/sbin/ifconfig docker0|grep inet|head -1|sed 's/\:/ /'|awk '{print $3}'`

# Handle Options
while [[ $# -gt 1 ]]
do
key="$1"

case $key in
    -p|--port)
    WALLET_API_PORT="$2"
    shift # past argument
    ;;
    -a|--alias)
    ALIAS="$2"
    shift # past argument
    ;;
    -v|--version)
    WALLET_API_VERSION="$2"
    ;;
    *)
          # unknown option
    ;;
esac
shift # past argument or value
done

remove_existing() {
	docker rm -f wallet-api-$WALLET_API_VERSION-$ALIAS
}

echo "... Removing existing container wallet-api-$WALLET_API_VERSION-$ALIAS"
remove_existing || true
echo "... Starting new container wallet-api-$WALLET_API_VERSION-$ALIAS"

docker run -d --name "wallet-api-$WALLET_API_VERSION-$ALIAS" -v wallet-data-volume:/wallet -p $WALLET_API_PORT:8080 -e WALLET_ALIAS=$ALIAS --restart=always \
	-e JASYPT_ENCRYPTOR_PASSWORD="$ENCRYPT_PASSCODE" \
	\
	-e POINTCOIN_MQSQL_HOST="$MYSQL_HOST" \
	-e POINTCOIN_MQSQL_PORT="$MYSQL_PORT" \
	-e POINTCOIN_MQSQL_DATABASE="$MYSQL_DATABASE" \
	-e POINTCOIN_MQSQL_USER="$MYSQL_USER" \
	-e POINTCOIN_MQSQL_PASSWORD="ENC($MYSQL_PASSWORD)" \
	\
	-e POINTCOIN_SECURITY_ALLOWIP="127.0.0.1;$WALLET_API_HOST;$WALLET_DOCKER_HOST;$XUP_HOST;$MEM_HOST" \
	\
	-e POINTCOIN_TASK_DR_PUSH_URL="$XUP_DR_URL" \
	-e POINTCOIN_TASK_DR_PUSH_USER="$XUP_DR_USER" \
	-e POINTCOIN_TASK_DR_PUSH_PASSWORD="ENC($XUP_DR_PASSWORD)" \
	\
	-e POINTCOIN_SECURITY_USER_PASSWORD="ENC($USER_PASSWORD)"	\
	-e POINTCOIN_SECURITY_ADMIN_PASSWORD="ENC($USER_PASSWORD)"	\
	\
	kevinwangcy/upchain:pointcoin-wallet-api-$WALLET_API_VERSION

sleep 20 

echo "... Checking API Status"
curl -v http://$WALLET_API_HOST:$WALLET_API_PORT/api/echo

echo 

echo "Done."



