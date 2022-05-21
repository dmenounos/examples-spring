#!/bin/bash

IMAGE="dmen/tomcat:9.0"
CONTAINER="tomcat-template"

case $1 in
init)
	(set -x; docker container run -d -it --rm \
	-v "$(pwd)/template-ui/target/template-ui":"/opt/tomcat/webapps/template-ui" \
	--name $CONTAINER \
	$IMAGE)
	;;
start)
	(set -x; docker container start $CONTAINER)
	;;
stop)
	(set -x; docker container stop $CONTAINER)
	;;
rm)
	(set -x; docker container rm $CONTAINER)
	;;
logs)
	(set -x; docker container logs $CONTAINER)
	;;
exec)
	if [ "$#" -eq "1" ]; then
		echo
		echo "Run a command as root:"
		echo "$0 exec bash -l"
		echo
		echo "Log as user:"
		echo "$0 exec su -l tomcat -s /bin/bash"
		echo
		exit 1
	fi

	shift
	(set -x; docker container exec -it $CONTAINER "$@")
	;;
ip)
	(set -x; docker container inspect $CONTAINER | grep IPAddress | grep -v null | head -1 | cut -d '"' -f 4)
	;;
*)
	echo 1>&2
	echo "Usage: $0 { init | start | stop | rm | logs | exec | ip }" 1>&2
	echo 1>&2
	exit 1
	;;
esac
