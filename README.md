# Advent of Code - Day 2

My solution to [Advent of Code Day 2](https://adventofcode.com/2023/day/2).

I created a regex pattern to find the number of cubes for each color and get the color itself.

At first, I wrote it wrong :

`^((\d) (red|green|blue),? ?){1,3}$`

This did match on a line but would capture only the last cube color, number pair. 

I asked CHATGPT and it told me that this was happening because the outer capture group (outer parentheses) overwrote the repeating inside groups. Silly me! It makes sense!

So I fixed this and here is the pattern:

`(\d+) (red|green|blue)(?:, ?|$)` 

which:

- matches a digit 1 or more times and captures it.
- captures the color
- parses but doesn' capture the spacing between ", " and end of line if its the last color.

For part 2, I realized I didn't need to split on ";" which seperates subsets. I can just add it to the non-capturing group in the end. Therefore I tweaked the pattern and used this:

`(\\d+) (red|green|blue)(?:, ?|;|$)`.

This was a fun little algorithm to write!