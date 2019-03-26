# CDODebugUtils

Displays settings of CDOObjects as fields. Usable via logical structure view.

## Building the plugin

### Maven

1. Download and install Eclipse IDE 2019-03 (for Enterprise Java developers).
2. File -> Import -> Existing Projects into Workspace -> Next -> Select root directory -> REPOSITORY_PATH -> Select "parent" -> Finish
3. Right click on the project -> Run as -> Maven install

### Manually

1. Download and install Eclipse IDE 2019-03 (for Enterprise Java developers).
2. File -> Import -> Existing Projects into Workspace -> Next -> Select root directory -> REPOSITORY_PATH -> Select the option "Search for nested projects" -> Select "tools.mdsd.cdo.debug.variablesview" -> Finish
3. Right click on the project -> Run as -> Eclipse Application


## Running the examples

Note: all examples except for DemoTransientState require a running CDO server with a configured repository named "repo1".

1. Analogous to "Building the plugin - Manually" import following nested projects:

    * `cdotest`
    * `CDOWebPage`

2. Navigate to `cdotest/de.cooperateproject.repository.targetplatforms/neon.target` and click on the link "Set as Active Target Platform" in the upper right corner.

3. Right click on one of the classes in the `cdotest` package and select _Debug as_ -> _Java Application_.
