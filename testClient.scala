import scala.util.{Success, Failure}
import scala.concurrent.duration._
import akka.actor.ActorSystem
import akka.pattern.ask
import akka.event.Logging
import akka.io.IO
import spray.json._
import spray.can.Http
import spray.httpx.SprayJsonSupport
import spray.util._
import spray.client.pipelining._
import spray.http._
import scala.concurrent.Future
import akka.util.Timeout
import scala.collection.mutable.ArrayBuffer

class testClient{
  val dummyVal = 0
}
case class jsonParser(a :Int,b :Int)
object myProtocol extends DefaultJsonProtocol{
  implicit val j = jsonFormat2(jsonParser)
}
object Main extends App {
	    implicit val requestTimeout = Timeout(60 seconds)
			implicit val system = ActorSystem("spray-client-example")
			implicit val executionContext = system.dispatcher
			val clientPipeline = sendReceive
      var getUrlList = Array("http://panthera.api.yuppcdn.net/yuppsocial/social/api/popular/live",          
          "http://panthera.api.yuppcdn.net/yuppsocial/social/api/popular/catchup",
          "http://panthera.api.yuppcdn.net/yuppsocial/social/api/popular/movies",
          "http://panthera.api.yuppcdn.net/yuppsocial/social/api/popular/othercatchup",
          "http://panthera.api.yuppcdn.net/yuppsocial/social/api/episodes")      
      for(url <- getUrlList){        
    	  val response = clientPipeline {
    		  Get(url)
    	  }          	  
    	  response.onComplete{
    	    case Success(res) => {
              println(url)
    		      var json = res.entity.data.asString.parseJson.asJsObject
    				  var jsonMap = new JsObject(json.fields.seq)
              validate(url,jsonMap)
    	    }
    	    case _ => println("something happened")
    	  }        
     }
      def validate(url :String,json :JsObject): Boolean = {
        println(json.toString())
        return false
      }
}