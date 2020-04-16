import org.junit.jupiter.api.Test;

public class tests {
    @Test
    public void  tests1(){

        String s = "F:IDEA FilesJDBC_Projectsrcmainwebapp";

        String t = "F:IDEA FilesJDBC_ProjecttargetJDBC_Projectimages";

        System.out.println(t.indexOf("target"));;
        System.out.println(t.replace(t.substring(t.indexOf("target")),"src\\main\\webapp"));




    }
}
