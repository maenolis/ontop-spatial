#!/usr/bin/env bash
java -ea -cp /home/maenolis/.m2/repository/it/unibz/inf/ontop/ontop-quest-sesame/1.16.1/ontop-quest-sesame-1.16.1.jar:/home/maenolis/.m2/repository/it/unibz/inf/ontop/ontop-obdalib-owlapi3/1.16.1/ontop-obdalib-owlapi3-1.16.1.jar:/home/maenolis/.m2/repository/net/sourceforge/owlapi/owlapi-api/3.4.10/owlapi-api-3.4.10.jar:/home/maenolis/.m2/repository/it/unibz/inf/ontop/ontop-obdalib-sesame/1.16.1/ontop-obdalib-sesame-1.16.1.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-http-client/2.7.10/sesame-http-client-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-http-protocol/2.7.10/sesame-http-protocol-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-binary/2.7.10/sesame-rio-binary-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-datatypes/2.7.10/sesame-rio-datatypes-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-languages/2.7.10/sesame-rio-languages-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-nquads/2.7.10/sesame-rio-nquads-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-rdfjson/2.7.10/sesame-rio-rdfjson-2.7.10.jar:/home/maenolis/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.2.1/jackson-core-2.2.1.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-trig/2.7.10/sesame-rio-trig-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-trix/2.7.10/sesame-rio-trix-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-repository-sparql/2.7.10/sesame-repository-sparql-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-queryresultio-sparqlxml/2.7.10/sesame-queryresultio-sparqlxml-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-repository-contextaware/2.7.10/sesame-repository-contextaware-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-repository-dataset/2.7.10/sesame-repository-dataset-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-repository-http/2.7.10/sesame-repository-http-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-repository-manager/2.7.10/sesame-repository-manager-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-repository-event/2.7.10/sesame-repository-event-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-sail-api/2.7.10/sesame-sail-api-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-sail-memory/2.7.10/sesame-sail-memory-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-repository-sail/2.7.10/sesame-repository-sail-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-sail-nativerdf/2.7.10/sesame-sail-nativerdf-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-sail-inferencer/2.7.10/sesame-sail-inferencer-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-queryparser-serql/2.7.10/sesame-queryparser-serql-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-queryalgebra-evaluation/2.7.10/sesame-queryalgebra-evaluation-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-sail-federation/2.7.10/sesame-sail-federation-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-config/2.7.10/sesame-config-2.7.10.jar:/home/maenolis/.m2/repository/javax/servlet/servlet-api/3.0-alpha-1/servlet-api-3.0-alpha-1.jar:/home/maenolis/.m2/repository/org/json/org.json/chargebee-1.0/org.json-chargebee-1.0.jar:/home/maenolis/.m2/repository/it/unibz/inf/ontop/ontop-reformulation-core/1.16.1/ontop-reformulation-core-1.16.1.jar:/home/maenolis/.m2/repository/com/h2database/h2/1.4.187/h2-1.4.187.jar:/home/maenolis/.m2/repository/org/javabits/jgrapht/jgrapht-core/0.9.3/jgrapht-core-0.9.3.jar:/home/maenolis/.m2/repository/org/postgis/postgis-jdbc/1.3.3/postgis-jdbc-1.3.3.jar:/home/maenolis/.m2/repository/org/apache/tomcat/tomcat-juli/7.0.27/tomcat-juli-7.0.27.jar:/home/maenolis/.m2/repository/org/apache/tomcat/tomcat-jdbc/7.0.37/tomcat-jdbc-7.0.37.jar:/home/maenolis/.m2/repository/it/unibz/inf/ontop/ontop-quest-owlapi3/1.16.1/ontop-quest-owlapi3-1.16.1.jar:/home/maenolis/.m2/repository/ch/qos/logback/logback-classic/1.0.2/logback-classic-1.0.2.jar:/home/maenolis/.m2/repository/ch/qos/logback/logback-core/1.0.2/logback-core-1.0.2.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-queryparser-sparql/2.7.10/sesame-queryparser-sparql-2.7.10.jar:/home/maenolis/.m2/repository/it/unibz/inf/ontop/ontop-quest-db/1.16.1/ontop-quest-db-1.16.1.jar:/home/maenolis/.m2/repository/it/unibz/inf/ontop/ontop-obdalib-r2rml/1.16.1/ontop-obdalib-r2rml-1.16.1.jar:/home/maenolis/.m2/repository/net/sourceforge/owlapi/owlapi-apibinding/3.4.10/owlapi-apibinding-3.4.10.jar:/home/maenolis/.m2/repository/net/sourceforge/owlapi/owlapi-impl/3.4.10/owlapi-impl-3.4.10.jar:/home/maenolis/.m2/repository/net/sourceforge/owlapi/owlapi-parsers/3.4.10/owlapi-parsers-3.4.10.jar:/home/maenolis/.m2/repository/net/sourceforge/owlapi/owlapi-oboformat/3.4.10/owlapi-oboformat-3.4.10.jar:/home/maenolis/.m2/repository/net/sourceforge/owlapi/owlapi-tools/3.4.10/owlapi-tools-3.4.10.jar:/home/maenolis/.m2/repository/org/apache/felix/org.osgi.core/1.4.0/org.osgi.core-1.4.0.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-repository-api/2.7.10/sesame-repository-api-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-query/2.7.10/sesame-query-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-util/2.7.10/sesame-util-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-queryresultio-api/2.7.10/sesame-queryresultio-api-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-queryparser-api/2.7.10/sesame-queryparser-api-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-queryalgebra-model/2.7.10/sesame-queryalgebra-model-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-queryresultio-text/2.7.10/sesame-queryresultio-text-2.7.10.jar:/home/maenolis/.m2/repository/net/sf/opencsv/opencsv/2.0/opencsv-2.0.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-api/2.7.10/sesame-rio-api-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-turtle/2.7.10/sesame-rio-turtle-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-rdfxml/2.7.10/sesame-rio-rdfxml-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-n3/2.7.10/sesame-rio-n3-2.7.10.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-rio-ntriples/2.7.10/sesame-rio-ntriples-2.7.10.jar:/home/maenolis/.m2/repository/postgresql/postgresql/9.0-801.jdbc4/postgresql-9.0-801.jdbc4.jar:/home/maenolis/.m2/repository/org/optique-project/r2rml-api/0.1.3/r2rml-api-0.1.3.jar:/home/maenolis/.m2/repository/org/optique-project/r2rml-api-sesame-bridge/0.1.3/r2rml-api-sesame-bridge-0.1.3.jar:/home/maenolis/.m2/repository/commons-cli/commons-cli/1.1/commons-cli-1.1.jar:/home/maenolis/.m2/repository/org/slf4j/slf4j-log4j12/1.7.7/slf4j-log4j12-1.7.7.jar:/home/maenolis/.m2/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar:/home/maenolis/.m2/repository/commons-dbcp/commons-dbcp/1.3/commons-dbcp-1.3.jar:/home/maenolis/.m2/repository/commons-pool/commons-pool/1.5.4/commons-pool-1.5.4.jar:/home/maenolis/.m2/repository/commons-fileupload/commons-fileupload/1.2.1/commons-fileupload-1.2.1.jar:/home/maenolis/.m2/repository/commons-io/commons-io/2.4/commons-io-2.4.jar:/home/maenolis/.m2/repository/commons-httpclient/commons-httpclient/3.1/commons-httpclient-3.1.jar:/home/maenolis/.m2/repository/commons-codec/commons-codec/1.4/commons-codec-1.4.jar:/home/maenolis/.m2/repository/commons-validator/commons-validator/1.3.1/commons-validator-1.3.1.jar:/home/maenolis/.m2/repository/commons-beanutils/commons-beanutils/1.7.0/commons-beanutils-1.7.0.jar:/home/maenolis/.m2/repository/commons-digester/commons-digester/1.6/commons-digester-1.6.jar:/home/maenolis/.m2/repository/commons-collections/commons-collections/2.1/commons-collections-2.1.jar:/home/maenolis/.m2/repository/xml-apis/xml-apis/1.0.b2/xml-apis-1.0.b2.jar:/home/maenolis/.m2/repository/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar:/home/maenolis/.m2/repository/junit/junit/4.12/junit-4.12.jar:/home/maenolis/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/home/maenolis/.m2/repository/it/unibz/inf/ontop/ontop-obdalib-core/1.16.1/ontop-obdalib-core-1.16.1.jar:/home/maenolis/.m2/repository/org/openrdf/sesame/sesame-model/2.7.10/sesame-model-2.7.10.jar:/home/maenolis/.m2/repository/org/antlr/antlr/3.5.2/antlr-3.5.2.jar:/home/maenolis/.m2/repository/org/antlr/antlr-runtime/3.5.2/antlr-runtime-3.5.2.jar:/home/maenolis/.m2/repository/org/antlr/ST4/4.0.8/ST4-4.0.8.jar:/home/maenolis/.m2/repository/org/slf4j/slf4j-api/1.6.4/slf4j-api-1.6.4.jar:/home/maenolis/.m2/repository/com/google/guava/guava/15.0/guava-15.0.jar:/home/maenolis/.m2/repository/com/github/jsqlparser/jsqlparser/0.9.1/jsqlparser-0.9.1.jar:. ontop.temporal.test.runner.TemporalTestRunner