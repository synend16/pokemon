package com.endre.pokemon.util

import com.endre.pokemon.model.entity.Pokemon
import com.endre.pokemon.model.dto.PokemonDto

/**
 * Created by Endre on 02.09.2018.
 * news
 * Verson:
 */
class PokemonConverter {

    companion object {

        fun convertFromDto(pokemonDto: PokemonDto) : Pokemon {
            return Pokemon(pokemonDto.num!!,pokemonDto.name!!, pokemonDto.img!!, pokemonDto.candy_count,
                    pokemonDto.egg!!, pokemonDto.type, pokemonDto.weaknesses,
                    convertFromSimplePokemonDto(pokemonDto.prev_evolution),
                    convertFromSimplePokemonDto(pokemonDto.next_evolution)
            )
        }


        fun convertFromDto(pokemon: Iterable<PokemonDto>): List<Pokemon> {
            return pokemon.map { convertFromDto(it) }
        }


        fun convertToDto(pokemon: Pokemon): PokemonDto {
            return PokemonDto(
                    pokemon.id.toString(),
                    pokemon.num,
                    pokemon.name,
                    pokemon.img,
                    pokemon.candyCount,
                    pokemon.egg,
                    pokemon.type,
                    pokemon.weaknesses,
                    convertToSimplePokemonDto(pokemon.prevEvolution),
                    convertToSimplePokemonDto(pokemon.nextEvolution)
            )
        }

        fun convertToDto(pokemon: Iterable<Pokemon>): List<PokemonDto> {
            return pokemon.map { convertToDto(it) }
        }

        fun convertToSimplePokemonDto(pokemonNums: Set<String>?) : MutableSet<PokemonDto>?{
            return if (pokemonNums != null) {
                if (pokemonNums.isNotEmpty()) {
                    pokemonNums.asSequence().map { PokemonDto(num = it) }.toMutableSet()
                } else null
            }else {
                null
            }
        }

        fun convertFromSimplePokemonDto(simpleDtos: Set<PokemonDto>?): MutableSet<String>? {
            return if (simpleDtos != null) {
                if (simpleDtos.isNotEmpty()) {
                    simpleDtos.asSequence().map { it.num!! }.toMutableSet()
                } else null
            } else {
                null
            }
        }
    }
}