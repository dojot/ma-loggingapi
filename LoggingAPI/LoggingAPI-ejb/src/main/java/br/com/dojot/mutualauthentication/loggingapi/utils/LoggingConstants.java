package br.com.dojot.mutualauthentication.loggingapi.utils;

public interface LoggingConstants {

	String REDIS_CLUSTER_MASTER_NAME = "redis-cs-cluster";

	String TOPIC_LOGGING_PROCESSING = "logprocessingtopic";
	String TOPIC_LOGGING_TRANSACTION = "logtransactiontopic";
	String TOPIC_DEBUG_TRANSACTION = "debugtransactiontopic";
	String TOPIC_DEBUG_RELEASE_TRANSACTION = "debugreleasetransactiontopic";
	String TOPIC_DEBUG_CRYPTO_CHANNEL = "debugcryptochanneltopic";
	String TOPIC_DEBUG_COMPONENT = "debugcomponenttopic";
	String TOPIC_DEBUG_PAGE_PROTECTION = "debugpageprotectiontopic";
	
	String DELETE_OLD_LOGS = "DELETE_OLD_LOGS";
	
	// Cassandra Settings
	String CASSANDRA_DEFAULT_HOSTNAME_OR_IP = "127.0.0.1";
	String CASSANDRA_KEYSPACE_LOGGING = "logging";

	String PARAM_KAFKA_BOOTSTRAP_SERVERS = "PARAM_KAFKA_BOOTSTRAP_SERVERS";
	String PARAM_KAFKA_NUMBER_OF_CONSUMERS = "PARAM_KAFKA_NUMBER_OF_CONSUMERS";
}
