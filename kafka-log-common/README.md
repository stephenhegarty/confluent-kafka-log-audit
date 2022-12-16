## Kafka Log Common

This module houses the commonly used dependencies and plugins shared amongst the consumer
and producer modules. This is for reducing duplication of code and allows for single point of bumping
pom versions for dependencies.

Within in this module, it also auto generates the schema registry for `RawLogMessage` avro via the maven-avro-plugin. 
