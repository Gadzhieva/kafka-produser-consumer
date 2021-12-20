# Producer - Consumer

<p> This is a console application. It is intended to send 
records from a PostgreSQL database relation to Kafka topic and 
vice versa. To do that application has two modes: </p>

<ul>
  <li>producer. In this mode application will sent messages 
with database relation content to Kafka topic</li>
  <li>consumer. Application will read messages from Kafka
topic and put their content into database relation</li>
</ul>

## Prerequisites

To use the application you should have:
<ul>
  <li>Java version 17</li>
  <li>Kafka version 2.13-2.8.1</li>
  <li>Gradle version 7.3.2</li>
</ul>

<p>Before starting the application you need to do the following: </p>
<ol>
  <li>Make sure that Kafka server you are going to work with 
is running</li>
  <li>Make sure that relation in PostgreSQL database you want 
to work with has these exact columns: 
    <ol>
      <li>ID (Int)</li>
      <li>Name (String)</li>
      <li>Timestamp (DateTime)</li>
    </ol></li>
  <li>Check 'application.property' file. List of properties 
you should change:</li>
    <ol>
      <li>database connection parameters:
    <ul>
      <li>spring.datasource.username</li>
      <li>spring.datasource.password</li>
      <li>spring.datasource.url</li>
    </ul>
      </li>
      <li>Database relation name (in producer mode - name of the source table, 
in consumer mode - name of the target table):</li>
    <ul>
      <li>table.name</li>
    </ul>
      <li>Kafka server address: </li>
    <ul>
      <li>spring.kafka.bootstrap-servers</li>
    </ul>
      <li>Kafka topic name:</li>
    <ul>
      <li>topic.name</li>
    </ul>
      <li>Kafka consumers group Id:</li>
    <ul>
      <li>topic.groupId</li>
    </ul>
      <li>Application mode:</li>
    <ul>
      <li>application.mode</li>
    </ul>
    </ol>
</ol>