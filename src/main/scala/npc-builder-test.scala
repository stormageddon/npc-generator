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