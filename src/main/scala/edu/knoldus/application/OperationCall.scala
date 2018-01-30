package edu.knoldus.application

import edu.knoldus.model.Operation
import org.apache.log4j.Logger
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object OperationCall {

  def main(args: Array[String]): Unit = {

    val operation = new Operation
    val log = Logger.getLogger(this.getClass)
    val listOfFiles = operation.getFileNames("/home/knoldus/Desktop/folder1")
    listOfFiles onComplete {
      case Success(list) => log.info(list)
      case Failure(error) => log.info(error)
    }
    Thread.sleep(200)
    val fileList = operation.getFileNames("/home/knoldus/Desktop/folder4")
    fileList onComplete {
      case Success(list) => log.info(s"\n$list")
      case Failure(error) => log.info(s"\n$error")
    }
  }
}

