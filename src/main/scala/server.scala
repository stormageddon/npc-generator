package com.test.NpcServer

import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}
import java.io.{InputStream, OutputStream}
import java.net.InetSocketAddress
import scala.util.parsing.json._
import com.test.NpcBuilder._

object NpcServer {
    def main(args: Array[String]) {
        val server = HttpServer.create(new InetSocketAddress(8000), 0)
        server.createContext("/npc", new NpcHandler())
        server.createContext("/", new RootHandler())
        server.setExecutor(null)

        server.start()

        println("Hit any key to exit...")

        System.in.read()
        server.stop(0)
    }
}

class NpcHandler extends HttpHandler {
  def handle(t: HttpExchange) {
    //displayPayload(t.getRequestBody)
    sendResponse(t)
  }

  private def sendResponse(t: HttpExchange) {
    val jsonResponse = copyStream(t.getRequestBody, System.out)
    
    var npcRace: String = ""
    jsonResponse.get("race") match {
      case None => npcRace = ""
      case Some(s: String) => npcRace = s
    }

    var npcName: String = ""
    jsonResponse.get("name") match {
      case None => npcName = ""
      case Some(s: String) => npcName = s
    }

    var npcClass: String = ""
    jsonResponse.get("class") match {
      case None => npcClass = ""
      case Some(s: String) => npcClass = s
    }

    var npcTitle: String = ""
    jsonResponse.get("title") match {
      case None => npcTitle = ""
      case Some(s: String) => npcTitle = s
    }

    val npcMade = new NpcBuilder().withRace(npcRace).withName(npcName).withClass(npcClass).withTitle(npcTitle).build()
    val response = npcMade.printName()

    t.sendResponseHeaders(200, response.length())
    val os = t.getResponseBody
    os.write(response.getBytes())
    os.close()
  }

  private def displayPayload(body: InputStream): Unit ={
    println()
    println("******************** REQUEST START ********************")
    println()
    copyStream(body, System.out)
    println()
    println("********************* REQUEST END *********************")
    println()
  }

  private def copyStream(in: InputStream, out: OutputStream) : Map[String,String] = {
    var test = scala.io.Source.fromInputStream(in).mkString
    val parsed = JSON.parseFull(test)
    val jsonMap : Map[String, String] = parsed match {
      case None => Map[String, String]()
      case Some(m: Map[String, String]) => m
    }

    return jsonMap
  }
}

class RootHandler extends HttpHandler {

  def handle(t: HttpExchange) {
    displayPayload(t.getRequestBody)
    sendResponse(t)
  }

  private def displayPayload(body: InputStream): Unit ={
    println()
    println("******************** REQUEST START ********************")
    println()
    copyStream(body, System.out)
    println()
    println("********************* REQUEST END *********************")
    println()
  }

  private def copyStream(in: InputStream, out: OutputStream) {
    Iterator
      .continually(in.read)
      .takeWhile(-1 !=)
      .foreach(out.write)
  }

  private def sendResponse(t: HttpExchange) {
    val response = "Ack!"
    t.sendResponseHeaders(200, response.length())
    val os = t.getResponseBody
    os.write(response.getBytes)
    os.close()
  }

}

