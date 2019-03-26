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

1. Download and install Eclipse IDE 2019-03 (for Enterprise Java developers).

2. Analogous to "Building the plugin - Manually", import following nested projects:

    * `de.cooperateproject.repository.product`
    * `cdotest`
    * `CDOWebPage`

3. Navigate to `cdotest/de.cooperateproject.repository.targetplatforms/neon.target` and click on the link "Set as Active Target Platform" in the upper right corner.

4. Open `de.cooperateproject.repository.product/CooperateCDOServer.product` and click on the Launch button.

A warning will be printed to the console, similar to `"!MESSAGE CDO server configuration not found: /Applications/Eclipse.app/Contents/MacOS/configuration/cdo-server.xml"`.

Copy `REPOSITORY_PATH/test_projects/cdoserver/org.eclipse.emf.cdo.server.product/config/cdo-server.xml` to the path printed in the warning.

5. Click on the Stop button in the Console tab in Eclipse and launch the server again by clicking the Launch button in the product.

6. Debug one of the `Demo...` classes in the `cdotest` project as described in the comments on the bottom of the files.
