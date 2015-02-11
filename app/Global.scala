package authexample

import java.lang.reflect.Constructor

import securesocial.controllers.{MailTemplates, ViewTemplates}
import securesocial.core.RuntimeEnvironment
import securesocial.core.authenticator.{CookieAuthenticatorBuilder, HttpHeaderAuthenticatorBuilder}
import securesocial.core.services.{AuthenticatorService, UserService}

import play.api.Play.current

import edu.umass.cs.iesl.authstore._

object Global extends play.api.GlobalSettings {

  val appKey = "sstest"
  object MyRuntimeEnvironment extends RuntimeEnvironment.Default[AppUser.basic] {
    override val userService: UserService[AppUser.basic] = new IeslUserService(play.api.db.slick.DB, appKey)
    override lazy val authenticatorService: AuthenticatorService[AppUser.basic] = new AuthenticatorService[AppUser.basic](
      new CookieAuthenticatorBuilder[AppUser.basic](new IeslAuthenticatorStore(play.api.db.slick.DB, appKey), idGenerator),
      new HttpHeaderAuthenticatorBuilder[AppUser.basic](new IeslAuthenticatorStore(play.api.db.slick.DB, appKey), idGenerator)
    )

    override lazy val viewTemplates: ViewTemplates = new ViewTemplates.Default(this)
    override lazy val mailTemplates: MailTemplates = new MailTemplates.Default(this)
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    val instance  = controllerClass.getConstructors.find { c =>
      val params = c.getParameterTypes
      params.length == 1 && params(0) == classOf[RuntimeEnvironment[AppUser.basic]]
    }.map {
      _.asInstanceOf[Constructor[A]].newInstance(MyRuntimeEnvironment)
    }
    instance.getOrElse(super.getControllerInstance(controllerClass))
  }

}
