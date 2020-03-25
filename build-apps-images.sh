#!/bin/bash

cd service-registry/docker && sh build.sh

cd ../../
cd api-gateway/docker && sh build.sh

cd ../../
cd admin-server/docker && sh build.sh

cd ../../
cd locals_service_manager/docker && sh build.sh

cd ../../
cd meeting_service_manager/docker && sh build.sh