This is the Axon Server distribution.

For information about the Axon Framework and Axon Server,
visit https://docs.axoniq.io.

Running Axon Server
-------------------

Axon Server can run in a cluster (this requires a license). Since Axon Server does not know on initialization if
it should run standalone, or as a node in a cluster, you will have to configure this.

To start the server on a specific node run the command:

    java -jar axonserver.jar

On the first node (or to run standalone) initialize the cluster using:

    java -jar axonserver-cli.jar init-cluster

On the other nodes, connect to the first node using:

    java -jar axonserver-cli.jar register-node -h <first-node-hostname>

For more information on setting up clusters and context check the reference guide at:

https://docs.axoniq.io/reference-guide/axon-server/installation/local-installation/axon-server-ee

Once Axon Server is running you can view its configuration using the Axon Dashboard at http://<axonserver>:8024.
Instead of doing this initialization through the command line, you can also use the Axon Dashboard to initialize.

Release Notes for version 2025.0.2
----------------------------------
Bug fixes and improvements:

- Fix removing a node from the cluster does not properly remove the node (since 2025.0.1)
- Fix reconnect button in UI always disabled when access control is enabled

Release Notes for version 2025.0.1
----------------------------------

Bug fixes and improvements:

- Fix for high CPU usage when another node in the cluster shuts down
- Improve performance for appending events through a non-leader node
- Update the Spring Boot version to resolve CVE-2035-22235

Release Notes for version 2025.0.0
----------------------------------

Non-Axon Framework application integrations

Axon Server 2025.0 supports seamless integration with non-Axon Framework applications through HTTP(s) and RSocket.
This approach eliminates the need for a separate Axon Synapse installation.

Axon Server can perform the following actions:
- route commands and queries to HTTP(s) and RSocket endpoints
- publish events to HTTP(s) and RSocket endpoints (backed by persistent streams)

Clients can now:
- store events and snapshots in Axon Server through a new HTTP(s) operation
- invoke commands and queries through a new HTTP(s) operation
- read aggregate events and snapshots through a new HTTP(s) operation

Release Notes for version 2024.2.8
----------------------------------
Bug fixes and improvements:

- Fix removing a node from the cluster does not properly remove the node (since 2024.2.7)
- Fix reconnect button in UI always disabled when access control is enabled

Release Notes for version 2024.2.7
----------------------------------

Bug fixes and improvements:

- Fix for high CPU usage when another node in the cluster shuts down
- Improve performance for appending events through a non-leader node
- Update the Spring Boot version to resolve CVE-2035-22235

Release Notes for version 2024.2.6
----------------------------------

Bug fixes and improvements:

- Fix null pointer exception in install snapshot for _admin replication group
- Update axios version used in legacy UI

Release Notes for version 2024.2.5
----------------------------------

Bug fixes and improvements:

- Fix increasing H2 database size
- Fix issue with persistent streams not showing correctly with LDAP extension
- Fix for incorrect output in CLI for add node to replication group request
- Updated LDAP extension to resolve CVEs in included libraries
- Updated Spring Boot patch level to fix CVE-2025-22228
- Updated checks for available diskspace. Default warning level for replication log and event store filesystems is now 5GB. Error level for
replication log changed to 256MB, for event store filesystems 512MB.

Release Notes for version 2024.2.4
----------------------------------

Bug fixes and improvements:

- Fixed cluster initialization with access control enabled with a specified context
- No longer log the license content when license is obtained from Axon Console
- Update Spring Boot version to 3.3.9 to resolve vulnerabilities in Netty-handler and Json-smart libraries
- Update gRPC version to 1.71.0
- Fixes in Create context dialog for creating context and replication group and creating context in replication groups with multiple node roles
- Fix in persistent streams to avoid loop when resetting the stream position immediately after merging segments

Release Notes for version 2024.2.3
----------------------------------

Bug fixes and improvements:

- Updated access control for purging the event store in dev mode. The action now requires the `CONTEXT_ADMIN` role.
- Fixed a bug that caused problems when creating a new context in the presence of a user with permissions on `*`.
- Adjusted configdb parameters, so that the database is closed only once. This resolves exceptions being logged at shutdown.
- OAuth extension now takes context path into account when generating URLs.
- OAuth extension supports setting the desired algorithm for id token signature using the property `idTokenSignatureAlgorithm`.


Release Notes for version 2024.2.2
----------------------------------

Bug fixes and improvements:
- Fixed auto-cluster issue when internal-domain property is set in axonserver properties.
- Moved option to export traces to Jaeger to an extension. To migrate, move the jar files in the "extensions/axon-server-extensions-jaeger-2024.2.2.zip" to the "exts" directory in the Axon Server installation directory.


Release Notes for version 2024.2.1
----------------------------------

Bug fixes and improvements:
- Updated packaged versions of LDAP and OAuth extensions

Release Notes for version 2024.2.0
----------------------------------

AxonServer 2024.2 requires Java version 17 or higher.

Bug fixes and improvements:
- Improved the stability of changes to cluster topology.
  When changing cluster topology, e.g. adding or removing nodes from replication groups, previously occurring instabilities have been fixed by revising the implementation of configuration changes.
- Improved performance of message routing with many clients.
  If the environment contains many client applications (more than 500), there previously were noticeable performance impacts.
  These have been fixed and explicit configuration options are provided.
- All-new AxonServer grafana dashboard.
  The dashboard we provide for grafana was completely reworked from scratch to simplify interpretation while at the same time improve accuracy.
  The new dashboard reflects the new metrics introduced in previous versions.
- Fixed a bug in the search screen resulting in results from the previous search to be mixed with the current one.
- Better integration in Axoniq Console
- Upgraded dependencies.
  AxonServer was upgraded to the latest dependencies available wherever possible.
- Adding a node with an already existing node name now fails gracefully
- Improved contents of diagnostics package
- Ephemeral contexts now remove entries from the global index

Release Notes for version 2024.1.4
----------------------------------
Bug fixes and improvements:
- Redistribute clients across Axon Server nodes when a node is restarted with access control enabled
- UI fixes for non-admin users
  * username and roles visible on top of the page
  * user's contexts are now visible in the dropdown list for Search/Commands/Queries/Events pages
  * user's contexts are now visible in the Context page

Release Notes for version 2024.1.3
----------------------------------
Bug fixes and improvements:
- Axon Server node should return information about the clients connected to that node only
- Backing up the RAFT log entries should take into account replication group rather than context
- Record handling duration for initial query of a subscription query
- Persistent streams improvements:
 * Prevent closing the stream for slow clients
 * Improved error handling

Release Notes for version 2024.1.2
----------------------------------
Bug fixes and improvements:
- Redistribute clients across Axon Server nodes when a node is restarted
- Event processor operations fail when the processing group contains a forward slash
- Potential replication issue when trying to apply events for already closed contexts during shutdown of Axon Server
- Increased maximum length for the username to 255 characters
- Update the event store size when a new index file is created
- Visual improvements in search table: headers not visible by default & action not visible by default
- Improved logging in the event store
- Stop replication applying process when the replication group is stopped
- Global Index pre-load for configured contexts

Release Notes for version 2024.1.1
----------------------------------

Bug fixes and improvements:
- Revert optimization in replication from version 2023.2.4, as it could lead to a node entering fatal state
- Stop Axon Server from redirecting a client to a node that is in fatal state
- Reduce communication between the leader and follower and logging when a node is starting up
- Search page improvements
- Set correct permissions for persistent stream API calls
- Add validation of newly created index files
- Allow non-pristine clusters to connect to Console
- Fix the event store size in the context page


Release Notes for version 2024.1.0
----------------------------------

Persistent streams

Persistent streams provide the option to open an event stream from a client and let Axon Server track the progress. This
was already available as a preview version in 2024.0, but is now available by default. Persistent streams are supported
in Axon Framework 4.10 as an alternative to tracking or pooled streaming event processors.

For more information see https://library.axoniq.io/axon_framework_old_ref/events/event-processors/subscribing.html in the Axon Framework section.

Bug fixes and improvements

- Prevent stale threads when an Axon Server node closes the connection to another node
- Clean up metrics from disconnected clients
- prevent WARN log messages when a query completed message was received from an unexpected client
- Allow context with ephemeral events without a license
- Fix for listing event processors when there are more than 512 event processors

Docker image changes

The default Java version for the Docker images has changed from Java 11 to Java 17. This means that the docker images with tag "latest", "latest-nonroot", "2024.1.0", and "2024.1.0-nonroot" use Java 17. Java 11 based images are still available with the "-jdk-11" extensions in the tag name.

Dependency updates

- gRPC version updated to 1.65.1

Release Notes for version 2024.0.4
----------------------------------
Fixes and improvements:
- Fix for a problem starting up Axon Server with plugins configured
- Removed race condition causing possible delay in receiving first event on newly registered event handler
- Improve diagnostics package to contain full log information when "logging.config" property is set

Release Notes for version 2024.0.3
----------------------------------
Fixes and improvements:
- Add an option to reduce the number of global index segments Axon Server checks when the first event for a new
  aggregate is stored. This can be configured globally with the property
  "axoniq.axonserver.event.global-index-segments-check" or on a context level with the property
  "event.global-index-segments-check". The value is the number of global index segments to check, with a
  minimal value of 2.
- Fix for Control DB migration in case of plugin configuration properties with long values
- Updating a license through Axon Console now takes effect immediately
- Improved distribution of queries to different instances of the query handlers

New configuration parameter:
- axoniq.axonserver.event.global-index-segments-check=Integer.MAX_VALUE

Release Notes for version 2024.0.2
----------------------------------

Fixes and improvements:
- Updating a license through Axon Console now takes effect immediately
- Reduced memory usage for internal communication
- Reduced the number of threads used with large number of contexts
- UI improvements
  * the dialogs for adding replication groups, API tokens and users were not always cleared when opened
  * show the number of events in each context
  * improved notification when the current version is not the latest one
  * add option to set X-Frame-Options to SAMEORIGIN in the response messages

New configuration parameters:
- axoniq.axonserver.accesscontrol.same-origin=true (sets the X-Frame-Options header to SAMEORIGIN)
- axoniq.axonserver.event-store-background-thread-count=8
- axoniq.axonserver.event-store-processors-thread-count=8

Release Notes for version 2024.0.1
----------------------------------

Fixes and improvements:
- Fix the increasing number of threads on the running Axon Server nodes when one node in the cluster is down.
- Small fixes in the replication process:
    * remove delay in starting to synchronize with a node that is far behind
    * improve the performance for a follower catching up with the leader
    * prevent situations where a follower attempts to apply replication log entries that were already included in a snapshot
- Fix for authentication issue when multiple applications have the same token
- UI, copy token to clipboard fails when not running on a trusted URL
- UI, improved validations for applications, replication groups and contexts operations
- Improved handling for missing connection to Axon Console
- Support for Google Marketplace licenses
- Axon Server now performs a clean shutdown when it was started with incorrect node name or internal hostname/port

Release Notes for version 2024.0.0
----------------------------------

Database Update

Updated H2 database to store the Control DB, addressing some issues from previous H2 version (see the upgrade instructions in upgrade-instructions.txt or https://library.axoniq.io/axon-server-upgrade/upgrading_as_2024.html).


New Features and Improvements:
* Redesigned User Interface: The UI has been completely revamped with a modern look and feel for a better user experience. The changes include:
 - Simplified Overview Page: Access node information easily with filtering and scaling options.
 - Dedicated License Page: Track license expiry dates and view available features for non-enterprise users.
 - Monitoring Page: View important health information, display logs, and download diagnostic packages.
 - System Tasks: List and cancel running system tasks.
 - Search Event Store Page: Improved usability with removable columns, formatted code styles, and auto-composable queries.
 - Command and Queries Pages: Revamped for a better overview of messages in the system.
 - Long-Running Commands Component: View and cancel commands running longer than 1 second.
 - Scheduled Events Page: View and cancel scheduled events.
 - Streams Page (Experimental): Accessible for persistent streams if dev mode is enabled.
 - API Tokens (formerly Applications): Renamed for clarity, with improved token management.
 - Support for Wide Screens and Dark/Light Themes: Enhanced viewing experience.
 - Connection, Health, and Early Event Processor Issue Detection: Improved issue detection and resolution.
 - Embedded Documentation Snippets: Access documentation directly within the UI.
* Preview of new persistent streams feature, event streams where Axon Server manages the publication of events to clients and keeps track of the progress. This feature is enabled when development mode is enabled or when axoniq.axonserver.preview.persistent-streams property is set to true.


For release notes on earlier releases (Standard Edition and Enterprise Edition) check the release notes pages in the reference guide (https://docs.axoniq.io/reference-guide/release-notes/rn-axon-server).

Configuring Axon Server
=======================

Axon Server uses sensible defaults for all of its settings, so it will actually
run fine without any further configuration. However, if you want to make some
changes, below are the most common options. You can change them using an
"axonserver.properties" file in the directory where you run Axon Server. For the
full list, see the Reference Guide. https://docs.axoniq.io/reference-guide/axon-server

* axoniq.axonserver.name
  This is the name Axon Server uses for itself. The default is to use the
  hostname.
* axoniq.axonserver.hostname
  This is the hostname clients will use to connect to the server. Note that
  an IP address can be used if the name cannot be resolved through DNS.
  The default value is the actual hostname reported by the OS.
* server.port
  This is the port where Axon Server will listen for HTTP requests,
  by default 8024.
* axoniq.axonserver.port
  This is the port where Axon Server will listen for gRPC requests,
  by default 8124.
* axoniq.axonserver.internal-port
  This is the port where Axon Server will listen for gRPC requests from other AxonServer nodes,
  by default 8224.
* axoniq.axonserver.event.storage
  This setting determines where event messages are stored, so make sure there
  is enough diskspace here. Losing this data means losing your Events-sourced
  Aggregates' state! Conversely, if you want a quick way to start from scratch,
  here's where to clean.
* axoniq.axonserver.snapshot.storage
  This setting determines where aggregate snapshots are stored.
* axoniq.axonserver.controldb-path
  This setting determines where Axon Server stores its configuration information.
  Losing this data will affect Axon Server's ability to determine which
  applications are connected, and what types of messages they are interested
  in.
* axoniq.axonserver.replication.log-storage-folder
  This setting determines where the replication logfiles are stored.
* axoniq.axonserver.accesscontrol.enabled
  Setting this to true will require clients to pass a token.

The Axon Server HTTP server
===========================

Axon Server provides two servers; one serving HTTP requests, the other gRPC.
By default, these use ports 8024 and 8124 respectively, but you can change
these in the settings as described above.

The HTTP server has in its root context a management Web GUI, a health
indicator is available at "/actuator/health", and the REST API at "/v1'. The
API's Swagger endpoint finally, is available at "/swagger-ui/index.html", and gives
the documentation on the REST API.
