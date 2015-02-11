package authexample.controllers

import securesocial.core._
import edu.umass.cs.iesl.authstore._

class Main (override implicit val env: RuntimeEnvironment[AppUser.basic]) extends SecureSocial[AppUser.basic] {

  def home = SecuredAction { implicit request =>
    Ok(views.html.Main.index(request.user.profile))
  }
 
}
