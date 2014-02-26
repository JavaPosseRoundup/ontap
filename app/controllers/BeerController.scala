package controllers

import play.api.mvc._
import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import services.BeerService

class BeerController(implicit val bindingModule: BindingModule) 
  extends Controller with Injectable {

  val beerService = inject[BeerService]

  def search(prefix: String) = play.mvc.Results.TODO
}