# WaveORM

WaveORM is a light & fast ORM for Android that maps objects to SQLite databases.
  - WaveORM consumes minimal memory.
  - WaveORM supports SQLite database updates.
  - WaveORM supports SQLite database updates.

Steps for installing waveorm to the app

###1. Add this for gradle dependency
	dependencies {
		...
		compile 'com.hummingwave:waveorm:1.0.1'
	}
	
###2. Add this for maven dependency
    <dependency>
    <groupId>com.hummingwave</groupId>
    <artifactId>waveorm</artifactId>
    <version>1.0.1</version>
    <type>pom</type>
    </dependency>
  

###3.  Add this lines in Manifest.xml
	<meta-data android:name="WaveORM_DATABASE" android:value="YOUR_DATABASE_NAME" />
	<meta-data android:name="WaveORM_VERSION" android:value="YOUR_DATABASE_VERSION_NUMBER" />

###4.  Make your application class extends WaveORMApplication 
	public class YOUR_APPLICATION_CLASS_NAME extends WaveORMApplication {

	}

###5. Add your application class to Manifest.xml
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

###6. Create model class and extend it to WaveORMRecord
	@Table(name = “YOUR_TABLE_NAME ”)
	public class YOUR_TABLE_NAME extends WaveORMRecord{

	}
	
The variables declared in the class, will be considered as column’s name for the table except for those, which is annotated with @Ignore annotation.

###7. To mark variable as primary key use @PrimaryKey annotation
	@Table(name = “YOUR_TABLE_NAME ”)
	public class YOUR_TABLE_NAME extends WaveORMRecord{
		@PrimaryKey
		String name;

		//To have local variable in the class annotate it with @Ignore
		@Ignore
		int phoneNumber;
	}

###8. Generate setters and getters


##Example for CRUD in WaveORM

####Save Record <br /> 
    Employee emp = new Employee(); 
    emp.setName("Chaitra");  
    emp.setID("027");  
    emp.save();  
  
or

    save(WaveORMArrayList<T> customDBArrayList)


####List Records
    Employee emp = new Employee();<br /> 
    List<Employee> empList = emp.listAll();<br /> 

Similarlly following methods can be used for listing record

    findRecord(String primaryKeyValue)
    fetchRecords(String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)


####Update record
    Employee emp = new Employee();
    emp.setName("Chaitra T");
    emp.update();
  
Similarlly following methods can be used for updating record

    update(String whereClause, String[] whereArgs)
    update(WaveORMArrayList<T> iterator)
    update(WaveORMArrayList<T> iterator, String whereClause, String[] whereArgs)


####Delete Record
    Employee emp = new Employee();
    emp.delete();

Similarly following method can be used for deleting record

    deleteAll()
    delete(WaveORMArrayList<T> iterator)
    delete(WaveORMArrayList<T> iterator, String whereClause, String[] whereArgs)

####For getting number of records in the table
    Employee emp = new Employee();
    emp.getCount();

Similarly this method can be used for getting count of records in table

    getCount(String selection)
    getCount(String selection, String[] selectionArgs)

#### Alter table
    1. Create folder named waveORM_upgrade in the assets folder.
    2. Create file with the name same as YOUR_DATABASE_VERSION with extension .sql (Example: 2.sql)


##Exceptions in WaveORM

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

