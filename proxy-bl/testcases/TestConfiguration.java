import com.user.name.school.configuration.*;
import com.user.name.school.configuration.pojo.*;
class TestConfiguration
{
	public static void main(String gg[])
	{
 		Configuration configuration = ConfigurationFactory.getConfiguration();
		System.out.println("Server: "+configuration.getServer());
		System.out.println("Port: "+configuration.getPort());
	}
}
