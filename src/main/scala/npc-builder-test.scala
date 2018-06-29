package com.test.NpcBuilder

import scala.util.{Random}

class NPC(val npcName: String = "", val npcClass: String = "", val npcTitle: String = "", val npcRace: String = "") {
  
  
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
  def r = Random

  var name = npcName
  

  var title = ""
  if (npcTitle != null && npcTitle != "") {
    title = npcTitle
  }
  else {
    title = titleChoices(r.nextInt(titleChoices.length : Int))
  }
  
  var characterClass = ""
  if (npcClass != null && npcClass != "") {
    characterClass = npcClass
  }
  else {
    characterClass = classChoices(r.nextInt(classChoices.length : Int))
  }
  
  var characterRace = ""
  if (npcRace != null && npcRace != "") {
    characterRace = npcRace
  }
  else {
    characterRace = raceChoices(r.nextInt(raceChoices.length : Int))
  }

  def level = r.nextInt(20) + 1
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

  var bName: String = nameChoices(r.nextInt(nameChoices.length : Int))
  println("bName " + bName)

  def withName(newName: String) : NpcBuilder = {
    this.bName = newName
    return this
  }

  def build(): NPC = {
    val newNpc : NPC = new NPC()
    println("bName 2 " + this.bName)
    newNpc.name = this.bName
    return newNpc
  }
}

object NpcGenerator {
  def main(args: Array[String]) {
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
    var npc = new NpcBuilder()
    
    if (npcName != null && npcName != "") {
      npc = npc.withName(npcName)
    }

    var npcMade = npc.build()
    println(npcMade.name + " " + npcMade.title + ", a level " + npcMade.level + " " + npcMade.characterRace + " " + npcMade.characterClass)

  }
}


