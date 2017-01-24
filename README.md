# TicTacToe
A simple Tic Tac Toe implementation with simple AI.

The AI works as follows:

At any point during the game, the AI will try each possible move (i.e. every position on the board that isn't
already occupied by a "mark", which is either an "X" or an "O").

1) For each move, if it can win in the next turn, it will make that move, if it has to draw next turn, likewise.

2) If this is not the case, it will try the move as the opponent and see if that move causes the opponent to win,
and if this holds, then will make that move to prevent/block the opponent from winning.

3) Otherwise, it will try to make the best move available using two heuristics:
     a) Assuming the computer makes this move, how many possible ways are there to win, in other words, how many
        winning combinations is this board part of?
     b) A special case occurs with the corners, if two different moves produce the same number of winning
        combinations and one move is on the corner, then the corner is preferred as a countermeasure against the
        opponent.

