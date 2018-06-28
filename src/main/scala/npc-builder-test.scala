package com.test.NpcBuilder

import scala.util.{Random}

class NPC(index: Int) {
//  def nameChoices: Array[String] = Array("Mike", 
//    "Argyn",
//    "Ulrich",
//    "Viktor",
//    "Dendrath",
//    "Clarence",
//    "Molloc",
//    "Gunmaar",
//    "Reindrath",
//    "Ogden",
//    "Jared")
  
  def nameChoices: Array[String] = Array("Jared", "Ethan", "Mike", "Dave")
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
  def r = Random

  //def name = nameChoices(r.nextInt(nameChoices.length : Int))
  def name = nameChoices(index)
  def title = titleChoices(r.nextInt(titleChoices.length : Int))
  def characterClass = classChoices(r.nextInt(classChoices.length : Int))
  def level = r.nextInt(20) + 1
}

object NpcBuilder {
  def main(args: Array[String]) {
    while(true) {
      println("How many NPCs do you want to generate?")
      val numNPCs = scala.io.StdIn.readInt()
      println("Generating an npc on the fly!")

      for (i <- 0 to numNPCs - 1) {
        def npc = new NPC(i)
        println(npc.name + ", " + npc.title + ", a level " + npc.level + " " + npc.characterClass)
      }
    }
  }
}
