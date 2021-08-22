package v2017

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

class Day03 {
    companion object {

        /**
         * Formula from http://oeis.org/A214526
         */
        fun part1(input: Long): Long {
            if (input <= 1L) {
                return 0L
            }
            fun layer(n: Long): Long {
                return ceil(0.5 * sqrt(n.toDouble()) - 0.5).toLong()
            }
            return layer(input) + abs(((input - 1) % (2 * layer(input)) - layer(input)))
        }

        /**
         * Values from https://oeis.org/A141481/b141481.txt
         */
        fun part2(input: Long): Long {
            val values = listOf(
                1,
                1,
                2,
                4,
                5,
                10,
                11,
                23,
                25,
                26,
                54,
                57,
                59,
                122,
                133,
                142,
                147,
                304,
                330,
                351,
                362,
                747,
                806,
                880,
                931,
                957,
                1968,
                2105,
                2275,
                2391,
                2450,
                5022,
                5336,
                5733,
                6155,
                6444,
                6591,
                13486,
                14267,
                15252,
                16295,
                17008,
                17370,
                35487,
                37402,
                39835,
                42452,
                45220,
                47108,
                48065,
                98098,
                103128,
                109476,
                116247,
                123363,
                128204,
                130654,
                266330,
                279138,
                295229,
                312453,
                330785,
                349975,
                363010,
                369601,
                752688,
                787032,
                830037,
                875851,
                924406,
                975079,
                1009,
                57,
                1026827,
                2089141,
                2179400,
                2292124,
                2411813,
                2539320,
                2674100,
                2814493,
                2909666,
                2957731,
                6013560,
                6262851,
                6573553,
                6902404,
                7251490,
                7619304,
                8001525,
                8260383,
                8391037,
                17048404,
                17724526,
                18565223,
                19,
                52043,
                20390510,
                21383723,
                22427493,
                23510079,
                24242690
            )
            return values.first { it > input }.toLong()
        }
    }
}
