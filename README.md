# Spring 2020 CS319 Project - Slay the Spire

This project is developed by group 1D, including:

- [Özge Yaşayan](https://github.com/ozgey99)
- [Bora Kurucu](https://github.com/BoraKurucu)
- [Fatih Karahan](https://github.com/kyroath)
- [Gülnihal Koruk](https://github.com/gulnihalk)
- [Batuhan Özçömlekçi](https://github.com/spencereid)

## Description of the project

> This project is a clone of the very popular PC game Slay the Spire, made from scratch for CS319 course.

Slay the Spire is a single player roguelike deck building game. The game is based on a character moving upwards on a tower while fighting enemies using the cards in their deck. The tower consists of three different acts, each end in a boss fight. Each act has a set of bosses that are different for each act. To reach the act boss, player has to climb the tower by clearing rooms. Each act usually consists of 45-55 rooms. Each room is connected to 1-3 different rooms, and a path of 15 rooms clears the floor, with the 16th room being the act boss. The player cannot move downwards or sideways on the level they are in, therefore the game forces the player to choose its path very carefully.

There are four different room types: Monsters, Elites, Rest Sites, Stores and Events.

Monsters are the most common rooms in the map, they're rooms that have a random enemy that the player has to slay to continue, and each victory ends with a card and gold. Elite rooms are different in the way that the enemies spawn in those rooms are usually much stronger and harder to deal with, and a victory in this room also results in a relic being rewarded. Rest Sites are rooms that the player can rest and heal up, or upgrade a card from their deck. Stores are rooms where the player can buy cards, relics, potions, as well as remove a card from their deck by paying gold earned during the course of their run. Events are rooms that have something random happening in them. They can range from fighting an enemy, a heal, an upgrade for adding a curse card into their card, etc. There are numerous events that can happen, and not all of them are positive events.

The fights are rather simple, they end when each of the enemies' or the player's health decreases to zero. To achieve this, the player can attack, or block the incoming attacks. The fights happen in a turn based style, with each fight starting with the player. Each turn player has a certain amount of energy, that is by default 3, but can be changed further by different relics, cards or potions, that they use to play cards. Each card costs a predetermined amount of energy to play. For example, the basic Strike card deals 7 damage to a single enemy and costs a single point of energy to play. By default, the player can see the intent of the enemy, and act accordingly. Enemies, like the player, can do multiple things: they can attack, block, buff themselves or steal gold from the player. By the end of each round, the cards that the player has in their hand gets put into the discard pile and the player draws new cards the next turn from the draw pile. When the draw pile is exhausted, the discard pile is shuffled back into the draw pile.

There are 5 different cards that can be added to the deck: Attack, Skill, Power, Status and Curse. Their results depend on the card description. On top of that, a card can also have a keyword, or an effect. For example, playing a certain card can cause the player to draw a new card, or a card can only be played once and then gets exhausted. Each card can also inflict a condition on the enemy or the player, like increasing their attacks or the damage they take from each attack. These conditions are temporary, and are reset at the end of each combat. Each card can also be upgraded to increase their effects or decrease their costs.

Relics, however are permanent effects that persist over each fight. A relic can give the player another point of energy, but can take the players ability to earn gold. Potions are rather similar to relics in the sense that they persist as well, but potions are consumables that don't have a cost that the player uses during their fights. For example, a block potion gives the player a certain amount of block for the turn it was used, or another potion can increase the energy player has for that turn.

### Features to be added/removed

### Deliverables
- Project Description: https://docs.google.com/document/d/1TpaQdBhhHZmUxeCiF9RuAUme1bVDMzTOLCOH7jja6bo/edit?usp=sharing
