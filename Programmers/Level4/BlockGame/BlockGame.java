package Programmers.Level4.BlockGame;
// 블록 게임
// https://programmers.co.kr/learn/courses/30/lessons/42894

import java.util.Arrays;

class Solution {
    int N;
    int M;

    int[][][] removableBlock = new int[][][] {{{1, 0}, {1, 1}, {1, 2}}, {{1, 0}, {2, 0}, {2, -1}},
            {{1, 0}, {2, 0}, {2, 1}}, {{1, 0}, {1, -1}, {1, -2}}, {{1, 0}, {1, -1}, {1, 1}}};

    int dispose = -1;

    public int solution(int[][] board) {
        N = board.length;
        M = board[0].length;

        int answer = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i][j] != 0 && board[i][j] != dispose) {
                    if (matchBlocks(board, i, j)) {
                        j = -1;
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    private void disposeBlock(int[][] board, int i, int j, int num) {
        if (i < 0 || i >= N || j < 0 || j >= M) {
            return;
        }

        if (board[i][j] != num) {
            return;
        }

        board[i][j] = dispose;
        disposeBlock(board, i - 1, j, num);
        disposeBlock(board, i + 1, j, num);
        disposeBlock(board, i, j - 1, num);
        disposeBlock(board, i, j + 1, num);
    }

    private boolean matchBlocks(int[][] board, int r, int c) {
        boolean find = false;
        int blockKind = 0;
        for (blockKind = 0; blockKind < 5; ++blockKind) {
            if (matchBlock(board, r, c, removableBlock[blockKind])) {
                find = true;
                break;
            }
        }

        if (find == false) {
            disposeBlock(board, r, c, board[r][c]);
            return false;
        }

        int left = c;
        int right = c;
        int[] blockHeights = new int[51];
        Arrays.fill(blockHeights, 51);
        blockHeights[c] = r;

        for (int i = 0; i < 3; ++i) {
            int index = c + removableBlock[blockKind][i][1];
            int tempHeight = r + removableBlock[blockKind][i][0];
            left = Math.min(left, index);
            right = Math.max(right, index);
            blockHeights[index] = Math.min(blockHeights[index], tempHeight);
        }

        boolean removable = true;
        for (int j = left; j <= right; ++j) {
            if (blockHeights[j] == r) {
                continue;
            }
            for (int i = 0; i < blockHeights[j]; ++i) {
                if (board[i][j] != 0) {
                    removable = false;
                    break;
                }
            }
        }

        if (!removable) {
            return false;
        }

        // remove
        board[r][c] = 0;
        for (int i = 0; i < 3; ++i) {
            int curC = c + removableBlock[blockKind][i][1];
            int curR = r + removableBlock[blockKind][i][0];
            board[curR][curC] = 0;
        }
        return true;
    }

    private boolean matchBlock(int[][] board, int i, int j, int[][] block) {
        for (int k = 0; k < 3; ++k) {
            int r = i + block[k][0];
            int c = j + block[k][1];
            if (r < 0 || r >= N || c < 0 || c >= M) {
                return false;
            }
            if (board[i][j] != board[r][c]) {

                return false;
            }
        }
        return true;
    }

    public static String matToString(int[][] mat) {
        String res = "\n";
        for (int[] row : mat) {
            res += Arrays.toString(row);
            res += "\n";
        }
        return res;
    }
}


class Solution1 {
    int N;
    int M;
    int[] heights;

    int[][][] removableBlock = new int[][][] {{{1, 0}, {1, 1}, {1, 2}}, {{1, 0}, {2, 0}, {2, -1}},
            {{1, 0}, {2, 0}, {2, 1}}, {{1, 0}, {1, -1}, {1, -2}}, {{1, 0}, {1, -1}, {1, 1}}};

    int dispose = -1;

    public int solution(int[][] board) {
        N = board.length;
        M = board[0].length;

        int answer = 0;
        heights = new int[M];

        updateHeights(board);
        // System.out.println(matToString(board));
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i][j] != 0 && board[i][j] != dispose) {
                    if (matchBlocks(board, i, j)) {
                        j = 0;
                        answer++;
                    }
                }
            }
        }
        // System.out.println(matToString(board));

        return answer;
    }

    private void updateHeights(int[][] board) {
        for (int j = 0; j < M; ++j) {
            heights[j] = maxHeight(board, j);
        }
    }

    private int maxHeight(int[][] board, int j) {
        for (int i = 0; i < N; ++i) {
            if (board[i][j] != 0) {
                return i;
            }
        }
        return N - 1;
    }

    private void disposeBlock(int[][] board, int i, int j, int num) {
        if (i < 0 || i >= N || j < 0 || j >= M) {
            return;
        }

        if (board[i][j] != num) {
            return;
        }

        board[i][j] = dispose;
        disposeBlock(board, i - 1, j, num);
        disposeBlock(board, i + 1, j, num);
        disposeBlock(board, i, j - 1, num);
        disposeBlock(board, i, j + 1, num);
    }

    private boolean matchBlocks(int[][] board, int r, int c) {
        boolean find = false;
        int blockKind = 0;
        for (blockKind = 0; blockKind < 5; ++blockKind) {
            if (matchBlock(board, r, c, removableBlock[blockKind])) {
                find = true;
                break;
            }
        }

        if (!find) {
            disposeBlock(board, r, c, board[r][c]);
            return false;
        }

        int left = c;
        int right = c;
        int[] blockHeights = new int[51];
        Arrays.fill(blockHeights, 51);
        blockHeights[c] = r;

        for (int i = 0; i < 3; ++i) {
            int index = c + removableBlock[blockKind][i][1];
            int tempHeight = r + removableBlock[blockKind][i][0];
            left = Math.min(left, index);
            right = Math.max(right, index);
            blockHeights[index] = Math.min(blockHeights[index], tempHeight);
        }

        boolean removable = true;
        // for (int j = left; j <= right; ++j) {
        // if (blockHeights[j] != r && blockHeights[j] > heights[j]) {
        // removable = false;
        // break;
        // }
        // }

        for (int j = left; j <= right; ++j) {
            if (blockHeights[j] == r) {
                continue;
            }
            for (int i = 0; i < blockHeights[j]; ++i) {
                if (board[i][j] != 0) {
                    removable = false;
                    break;
                }
            }
        }

        if (!removable) {
            // disposeBlock(board, r, c, board[r][c]);
            return false;
        }

        // remove
        board[r][c] = 0;
        for (int i = 0; i < 3; ++i) {
            int curR = r + removableBlock[blockKind][i][0];
            int curC = c + removableBlock[blockKind][i][1];
            board[curR][curC] = 0;
        }
        updateHeights(board);
        return true;
    }

    private boolean matchBlock(int[][] board, int i, int j, int[][] block) {
        for (int k = 0; k < 3; ++k) {
            int r = i + block[k][0];
            int c = j + block[k][1];
            if (r < 0 || r >= N || c < 0 || c >= M) {
                return false;
            }
            if (board[i][j] != board[r][c]) {
                return false;
            }
        }
        return true;
    }

    public static String matToString(int[][] mat) {
        String res = "\n";
        for (int[] row : mat) {
            res += Arrays.toString(row);
            res += "\n";
        }
        return res;
    }
}


public class BlockGame {
    public static void main(String[] args) {
        testSol(new int[][] {{2, 0, 4, 0, 0}, {2, 4, 4, 4, 3}, {2, 2, 3, 3, 3}, {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}}, 3);
        testSol(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
                {0, 0, 0, 0, 0, 0, 9, 9, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {8, 8, 8, 0, 0, 4, 4, 0, 0, 0},
                {8, 2, 7, 0, 0, 3, 4, 0, 0, 0}, {0, 2, 7, 7, 7, 3, 0, 0, 5, 5},
                {1, 2, 2, 0, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 2);
        testSol(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 2, 7, 0, 0, 3, 4, 0, 0, 0}, {0, 2, 7, 7, 7, 3, 0, 0, 5, 5},
                {1, 2, 2, 0, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 4);
        testSol(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 2, 0, 0, 7, 3, 4, 0, 0, 0}, {0, 2, 7, 7, 7, 3, 0, 0, 5, 5},
                {1, 2, 2, 0, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 4);
        testSol(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 2);
        testSol(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {6, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 0, 0, 3, 3, 0, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 2);
        testSol(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 6, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 6, 6, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 3);
        testSol(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 3, 6, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 6, 6, 6, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 2);
        testSol(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 6, 0, 0, 3, 0, 4, 0, 0, 0}, {6, 6, 6, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 3);
        testSol(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 6, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 6, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 6, 6, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 7, 0, 4, 4, 0, 0},
                {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}, {0, 0, 2, 0, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 4);
        testSol(new int[][] {{1, 1, 1, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}, 0);

    }

    static void testSol(int[][] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.solution(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
