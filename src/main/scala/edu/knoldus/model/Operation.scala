package edu.knoldus.model

import java.io.File
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Operation {

  def getFileNames(folderName: String): Future[List[File]] = Future {
    val directory = new File(folderName)
    if (directory.exists) {
      val folder = directory.listFiles.toList
      getFileNamesHelper(folder, List())
    }
    else {
      throw new Exception("No such folder exists")
    }
  }

  def getFileNamesHelper(folder: List[File], fileList: List[File]): List[File] = {
    folder match {
      case Nil => fileList
      case topFile :: restFiles =>
        if (topFile.isFile) {
          getFileNamesHelper(restFiles, topFile :: fileList)
        }
        else {
          getFileNamesHelper(restFiles ::: topFile.listFiles.toList, fileList)
        }
    }
  }
}

