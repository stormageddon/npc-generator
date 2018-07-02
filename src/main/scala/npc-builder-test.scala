package com.test.NpcBuilder

import scala.util.{Random}
import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}
import java.io.{InputStream, OutputStream}
import java.net.InetSocketAddress
import scala.util.parsing.json._
import scala.io.Source

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
  val nameChoices: Array[String] = Source.fromFile("/Users/mike/code/personal/scala/npc-builder/src/main/scala/names.txt").getLines.toArray
  val titleChoices: Array[String] = Source.fromFile("/Users/mike/code/personal/scala/npc-builder/src/main/scala/titles.txt").getLines.toArray
  val classChoices: Array[String] = Source.fromFile("/Users/mike/code/personal/scala/npc-builder/src/main/scala/classes.txt").getLines.toArray
  val raceChoices: Array[String] = Source.fromFile("/Users/mike/code/personal/scala/npc-builder/src/main/scala/races.txt").getLines.toArray
  
  var bName: String = nameChoices(r.nextInt(nameChoices.length))
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