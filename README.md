# WaveORM

WaveORM is a light & fast ORM for Android that maps objects to SQLite database.
  - WaveORM consumes minimal memory.
  - WaveORM supports SQLite database updates.

Steps for installing waveorm to the app

### 1. Add this for gradle dependency
	dependencies {
		...
		compile 'com.hummingwave:waveorm:3.2.7'
	}
	
### 2. Add this for maven dependency
    <dependency>
    <groupId>com.hummingwave</groupId>
    <artifactId>waveorm</artifactId>
    <version>3.2.7</version>
    <type>pom</type>
    </dependency>
  

### 3.  Add this lines in Manifest.xml
	<meta-data android:name="WaveORM_DATABASE" android:value="YOUR_DATABASE_NAME" />
	<meta-data android:name="WaveORM_VERSION" android:value="YOUR_DATABASE_VERSION_NUMBER" />
	<!--Optional if you want to enable log-->
	<meta-data android:name="WaveORM_ENABLE_LOG" android:value="true" />
    
 
 If In case any module is added in the project and to perform SQLite CRUD at module level add this
	
	<meta-data android:name="WaveORM_MODULE_PACKAGE_NAME" android:value="YOUR_MODULE_PACKAGE_NAME" />
 

 If In case flavour is added in the project and to perform SQLite CRUD at flaovur level add this, "YOUR_MAIN_APPLICATION_PACKAGE_NAME" is pacakge name given in manifest file 
	
	<meta-data android:name="WaveORM_APPLICATION_PACKAGE_NAME" android:value="YOUR_MAIN_APPLICATION_PACKAGE_NAME" />

### 4.  Make your application class extends WaveORMApplication 
	public class YOUR_APPLICATION_CLASS_NAME extends WaveORMApplication {

	}

### 5. Add your application class to Manifest.xml
	<application
	android:name=".YOUR_APPLICATION_CLASS_NAME "
	. . . >
	</application>

or

    If in case there is no application class in the app, add this line in Manifest.xml
	 <application
        	android:name="com.hummingwave.Application.WaveORMApplication"
      	. . . >
      	
	    </application>

or

    If in case your application class in the app, cannot extend to the WaveORMApplication, add the following lines to
    your application class
	
		@Override
    		public void onCreate() {
        		super.onCreate();
			 try {
        			 WaveORMContext.init(this);
        		} catch (WaveORMException e) {
            			e.printStackTrace();
        		}
    		}
    		
    		@Override
    		public void onTerminate() {
        		super.onTerminate();
        		WaveORMContext.terminate();
    		}

### 6. Create model class and extend it to WaveORMRecord
	@Table(name = “YOUR_TABLE_NAME ”)
	public class YOUR_CLASS_NAME extends WaveORMRecord{

	}
	
The variables declared in the class, will be considered as column’s name for the table except for those, which is annotated with @Ignore annotation.

### 7. To mark variable as primary key use @PrimaryKey annotation
	@Table(name = “YOUR_TABLE_NAME ”)
	public class YOUR_CLASS_NAME extends WaveORMRecord{
		@PrimaryKey
		String name;

		//To have local variable in the class annotate it with @Ignore
		@Ignore
		int phoneNumber;
	}

### 8. Generate setters and getters


## Example for CRUD in WaveORM

#### Save Record 
It will save the record to the table, if that record doesn't exists in the table, else it will throw an WaveORMException of Unique 
key constraint validation failed with the code 2.
   
    Employee emp = new Employee(); 
    emp.setName("Chaitra");  
    emp.setID("027");  
    emp.save();  
  
or

    WaveORMArrayList<Employee> employeeWaveORMArrayList = new WaveORMArrayList<>();
    ...
    employeeWaveORMArrayList.save();

#### Update record
It will save the record to the table, if that record doesn't exists in the table, else it will update the record in the table.
If the record contains primitive data types to retain it's value, before updating the record, fetch the record and then update the record,
else it will set the value of primitive data types to it's default values.
    
    Employee emp = new Employee();
    emp.setName("Chaitra T");
    emp.update();
  
Similarly following method can be used for updating record

        1. update(String whereClause, String[] whereArgs)
   
           Example :  
               Employee employee = new Employee();
               employee.setName("Chaitra");
               employee.update("empNo = ?", new String[]{"027"});
           
same methods can be used to save list, before saving list user has to define WaveORMArrayList as shown below:   
       
       1. update() 

           Example :  
                      WaveORMArrayList<Employee> employeeWaveORMArrayList = new WaveORMArrayList<>();
                      ...
                      employeeWaveORMArrayList.update();
                  
       2. update(String whereClause, String[] whereArgs)

            Example :  
                      WaveORMArrayList<Employee> employeeWaveORMArrayList = new WaveORMArrayList<>();
                      ...
                      employeeWaveORMArrayList.update();


#### List Records
    Employee emp = new Employee(); 
    List<Employee> empList = emp.listAll();

Similarly following methods can be used for listing record

    1. findRecord(String primaryKeyValue)

        Example:
                    Employee employee = new Employee();
                    Employee object = (Employee) employee.findRecord("027");
                    
    2. findRecord(int primaryKeyValue)

        Example:
                    Employee employee = new Employee();
                    Employee object = (Employee) employee.findRecord(027);
                    
    3. fetchRecords(String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)

         Example:
                    Employee employee = new Employee();
                    List object = employee.fetchRecords("empNo = ?", new String[]{"027"}, null, null, null, "1");     
                                   
    4. fetchRecordsWithPagination(int paginationOffset)

         Example:
                    Employee employee = new Employee();
                    WaveORMPaginationResult waveORMPaginationResult;
                    int pagination = 0;
                    
                    if(waveORMPaginationResult != null){
                        pagination = waveORMPaginationResult.getPaginationOffset();
                    }
                    
                    waveORMPaginationResult = employee.fetchRecordsWithPagination(pagination);
                 
By default fetchRecordsWithPagination will return 50 records, to get result from WaveORMPaginationResult use waveORMPaginationResult.getResultantList() this method, it will return
list of objects of type <T>.

#### Delete Record
    Employee emp = new Employee();
    emp.delete();

Similarly following method can be used for deleting record

    1. deleteAll()

        Example:
                    Employee employee = new Employee();
                    employee.deleteAll();
                    
    2. delete()

        Example:
                     WaveORMArrayList<Employee> employeeWaveORMArrayList = new WaveORMArrayList<>();
                     ...
                     employeeWaveORMArrayList.delete();
                    
    3. delete(String whereClause, String[] whereArgs)

        Example:
                     WaveORMArrayList<Employee> employeeWaveORMArrayList = new WaveORMArrayList<>();
                     ...
                     employeeWaveORMArrayList.delete("empNo = ?", new String[]{"027"});

#### For getting number of records in the table
    Employee emp = new Employee();
    emp.getCount();

Similarly this method can be used for getting count of records in table

    getCount(String selection)
    getCount(String selection, String[] selectionArgs)

#### Alter table
    1. Create folder named waveORM_upgrade in the assets folder.
    2. Create file with the name same as YOUR_DATABASE_VERSION with extension .sql (Example: 2.sql)
    3. If in case anything have to be inserted into the table, insert script can be written into the same table
     Example:
     		ALTER TABLE Employee ADD eMail VARCHAR;
		INSERT INTO Employee(empNo, name, phNo, eMail) VALUES ('123', 'Emp_1', 0123456789, "abc@abc.com");

#### Insert into table
    1. Create folder named insertScript in the assets folder.
    2. Create file with extension .sql (Example: TABLE_NAME.sql) 
    3. Write insert scripts inside the file
       Example:
		INSERT INTO Employee(empNo,name,phNo) VALUES ('123', 'Emp_1', 0123456789);
					
					OR

    1. Create folder named insertScript in the assets folder.
    2. Put zip file (Example: TABLE_NAME.zip) inside the folder which contains .sql files


## Exceptions in WaveORM

WaveORM will throw WaveORMException, which needs to be handled by the user. Exceptions thrown by WaveORM are listed below, 
and user can handle these exceptions by using getCode() method.

Example

    try{
    ...
    } catch(WaveORMException e){
      switch(e.getCode()){
        case 0:
          //do something.
          break;
        case 1:
        //do something.
          break;
        .
        .
        case 9:
        //do something.
          break;
      }
    }

|Exception Code |Exception Description|
|---------------|---------------------|
|0|Database name is not initialized in Manifest.xml|
|1|Database version is not initialized in Manifest.xml|
|2|Unique key constraint validation failed, while inserting record|
|3|The table doesn't have any columns, to-do CRUD operations|
|4|The model class is not annotated with @Table annotation|
|5|WaveORMContext has not been initialized properly. Make sure your Application class extends WaveORMApplication|
|6|WaveORMContext is not initialized, Add your application class name to manifest|
|7|Cannot find a Package name, to read database name and database version from Manifest.xml|
|8|Multiple Primary keys cannot be added to the same table|
|9|Where condition for deleting a record is null, set values for the object to delete the record|
|10|Not able to find the package "YOUR_PACKAGE_NAME", may be because of app package is different from app gradle applicationID.|
