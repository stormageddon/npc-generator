package com.test.NpcBuilder

import scala.util.{Random}
import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}
import java.io.{InputStream, OutputStream}
import java.net.InetSocketAddress
import scala.util.parsing.json._

class NPC(val npcName: String = "", val npcClass: String = "", val npcTitle: String = "", val npcRace: String = "") {


  def r = Random

  var name = npcName
  var title = npcTitle
  var characterClass = npcClass
  var characterRace = npcRace

  val level = r.nextInt(20) + 1

  def printName() : String = {
    return name + " " + title + ", a level " + level + " " + characterRace + " " + characterClass
  } 
}

case class NpcBuilder() {
  def r = Random
  val nameChoices: Array[String] = Array("Mal", 
      "Argyn",
      "Ulrich",
      "Viktor",
      "Dendrath",
      "Clarence",
      "Molloc",
      "Gunmaar",
      "Reindrath",
      "Ogden",
      "Tess",
      "Visca")

  def titleChoices: Array[String] = Array("the Enormous", 
    "Bone-breaker", 
    "the Chivalrous", 
    "the Destroyer", 
    "the Defiler",
    "the White Rose",
    "the Silver Storm",
    "Death's Shadow",
    "Oathbreaker")

  def classChoices: Array[String] = Array("Barbarian",
    "Bard",
    "Cleric",
    "Druid",
    "Fighter",
    "Monk",
    "Paladin",
    "Ranger",
    "Rogue",
    "Sorcerer",
    "Warlock",
    "Wizard")

  def raceChoices: Array[String] = Array("Wood Elf", 
    "Dusk Elf",
    "Rock Gnome",
    "Forest Gnome",
    "Human",
    "Goliath",
    "Tiefling",
    "Halfling",
    "Aarakocra",
    "Aasamir",
    "Deep Gnome",
    "Dragonborn",
    "Drow",
    "Half Dragon")
  
  var bName: String = nameChoices(r.nextInt(nameChoices.length : Int))
  var bTitle: String = titleChoices(r.nextInt(titleChoices.length))
  var bClass: String = classChoices(r.nextInt(classChoices.length))
  var bRace: String = raceChoices(r.nextInt(raceChoices.length))

  def withName(newName: String) : NpcBuilder = {
    if (newName != null && newName != "") {
      this.bName = newName
    }
    return this
  }

  def withTitle(newTitle: String) : NpcBuilder = {
    if (newTitle != null && newTitle != "") {
      this.bTitle = newTitle
    }
    return this
  }

  def withClass(newClass: String) : NpcBuilder = {
    if (newClass != null && newClass != "") {
      this.bClass = newClass
    }
    return this
  }

  def withRace(newRace: String) : NpcBuilder = {
    if (newRace != null && newRace != "") {
      this.bRace = newRace
    }
    return this
  }

  def build(): NPC = {
    val newNpc : NPC = new NPC()
    newNpc.name = this.bName
    newNpc.title = this.bTitle
    newNpc.characterClass = this.bClass
    newNpc.characterRace = this.bRace
    return newNpc
  }
}

object NpcGenerator {
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
    println("here")
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

object test {
def test() {
  println("What name should your npc have (leave blank for random)?")
  val npcName = scala.io.StdIn.readLine()

  println("What title should your npc have (leave blank for random)?")
  val npcTitle = scala.io.StdIn.readLine()

  println("What class should your npc have (leave blank for random)?")
  val npcClass = scala.io.StdIn.readLine()

  println("What race should your npc have (leave blank for random)?")
  val npcRace = scala.io.StdIn.readLine()

  println("Generating an npc on the fly!")

  //val npc = new NPC(npcName = npcName, npcRace = npcRace, npcClass = npcClass, npcTitle = npcTitle)
  //var npc = new NpcBuilder().build()
  //println("npc: " + npc) 


  //var npc = new NpcBuilder().withName(npcName).build()
  var npcMade = new NpcBuilder().withRace(npcRace).withName(npcName).withClass(npcClass).withTitle(npcTitle).build()

  println(npcMade.name + " " + npcMade.title + ", a level " + npcMade.level + " " + npcMade.characterRace + " " + npcMade.characterClass)
}
}

