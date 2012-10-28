import org.python.core.PyObject;
import org.python.core.PyException;
import org.python.util.PythonInterpreter;

public class PyEx {
  public static void main(String []args) throws PyException {

    PythonInterpreter interp = new PythonInterpreter();
    interp.exec(”firstNames=open(’/tmp/FirstNames.txt’,'r’)”);
    interp.exec(”lastNames=open(’/tmp/LastNames.txt’,'r’)”);
    interp.exec(”fNlines=firstNames.readlines()”);
    interp.exec(”lNlines=lastNames.readlines()”);
    interp.exec(”fullNames=[x+y for x in fNlines for y in lNlines]”);
    PyObject completeList = interp.get(”fullNames”);

    System.out.println("Full names : "+ completeList);
  }
}