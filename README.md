# Spring 2020 CS319 Project - Slay the Spire

This project is developed by group 1D, including:

- [Özge Yaşayan](https://github.com/ozgey99)
- [Bora Kurucu](https://github.com/BoraKurucu)
- [Fatih Karahan](https://github.com/kyroath)
- [Gülnihal Koruk]
- [Batuhan Özçömlekçi]

## Description of the project

> This project is a clone of the very popular PC game Slay the Spire, made from scratch for CS319 course.

Slay the Spire is a single player roguelike deck building game. The game is based on a character moving upwards on a tower while fighting enemies using the cards in their deck. Tower consists of three different acts, that each end in a boss fight. Each act has a set of bosses that are different for each act. To reach the act boss, player has to climb the tower by clearing rooms. Each act usually consists of 45-55 rooms. Each room is connected to 1-3 different rooms, and a path of 15 rooms clears the floor, with the 16th room being the act boss. The player cannot move downwards or sideways on the level they are in, therefore the game forces the player to choose its path very carefully.

There are four different romm types: Monsters, Elites, Rest Sites, Stores and Events.

Monsters are the most common rooms in the map, they're romms that have a random enemy that player has to slay to continue, and each victory ends with a card and gold. Elite rooms are different only that the enemies spawn in those room are usually much stronger and harder to deal with, and a victory on this room also results in a relic being rewarderd. Rest Sites are rooms that player can rest and heal up, or upgrading a card from their deck. Stores are rooms where player can buy cards, relics, potions, as well as remove a card from their deck by paying gold earned during the course of their run. Events are rooms that have something random happening in them. They can range from fighting an enemy, a heal, an upgrade for adding a curse card into their card, etc. There are numerous events that can happen, and not all of them are positive events.

The fights are rather simple in their base, they end when each of the enemies' or the player's health reaches zero. To achieve this, the player can attack, or block the incoming attacks. The fights happen in a turn based style, with each fight starting with the player. Each turn player has a certain amount of energy, that is by default 3, but can be changed further by different relics, cards or potions, that they use to play cards. Each card costs a predetermined amount of energy to play. For example, the basic Strike card deals 7 damage to a single enemy and costs a single point of enery to play. By default, player can see the intent of the enemy, and act accordingly. Enemies, like player, can do multitude of things: they can attack, block, buff themselves or steal gold from the player. By the end of each round, the cards player has in their hand gets put into the discard pile and player draws new cards the next turn from the draw pile. When the draw pile is exhausted, the discard pile is shuffled back into the draw pile.

There are 5 different cards than can be added to the deck: Attack, Skill, Power, Status and Curse. Their results depend on the card description. On top of that, a card can also have a keyword, or an effect. For example, playing a certain card can cause the player to draw a new card, or a card can only be played once and then gets exhausted. Each card can also inflict a condition on the enemy or the player, like increasing their attacks or the damage they take from each attack. These conditions are temporary, and are reset at the end of each combat. Each card can also be upgraded to increase their effects or decrease their costs.

Relics, however are permament effects that persist over each fight. A relic can give the player another point of energy, but can take the players ability to earn gold. Potions are rather similar to relics in that they persist as well, but potions are consumables that doesn't have a cost that player uses during their fights. For example, a block potion gives the player a certain amount of block for the turn it was used, or another potion can increase the energy player has for that turn.

### Features to be added/removed
