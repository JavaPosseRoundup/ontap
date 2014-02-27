import com.escalatesoft.subcut.inject._
import config.StandardBindings
import play.GlobalSettings
import scala.language.reflectiveCalls

class Global extends GlobalSettings {

  override def getControllerInstance[A](controllerClass: Class[A]): A = {

    val bindingModule: BindingModule = StandardBindings

    try {
      controllerClass.newInstance()
    }
    catch {
      case reflex: InstantiationException =>
        val injectedConstructor = controllerClass.getDeclaredConstructor(classOf[BindingModule])
        injectedConstructor.newInstance(bindingModule)
    }
  }
  
}
