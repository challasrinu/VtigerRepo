package Generic_Utilities;
/**
 * This interface contains external file paths and JDBC URL
 * @author challa
 */
public interface IConstantPath {
String JDBC_URL="jdbc:mysql://localhost:3306/adv_sel";
String PROPERTIES_FILE_PATH=System.getProperty("user.dir")+
  "/src/test/resources/Vtiger.properties";
String EXCEL_FILE_PATH=System.getProperty("user.dir"+"/src/test/resources/VtigerCRMTestData.xlsx");

}
