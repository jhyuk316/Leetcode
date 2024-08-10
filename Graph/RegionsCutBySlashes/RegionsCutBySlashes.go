package RegionsCutBySlashes

// 959. Regions Cut By Slashes
// https://leetcode.com/problems/regions-cut-by-slashes/description/?envType=daily-question&envId=2024-08-10

import "fmt"

func regionsBySlashes(grid []string) int {
	n := len(grid) * 3

	mat := make([][]int, n)
	for i := range len(mat) {
		mat[i] = make([]int, n)
	}

	for i, row := range grid {
		runes := []rune(row)
		for j, col := range runes {
			switch col {
			case '\\':
				mat[i*3][j*3] = 1
				mat[i*3+1][j*3+1] = 1
				mat[i*3+2][j*3+2] = 1
			case '/':
				mat[i*3][j*3+2] = 1
				mat[i*3+1][j*3+1] = 1
				mat[i*3+2][j*3] = 1
			case ' ':
				continue
			}
		}
	}

	//for _, row := range mat {
	//	fmt.Println(row)
	//}

	count := 0
	for i := range n {
		for j := range n {
			if mat[i][j] == 0 {
				dfs(i, j, mat)
				count++
			}
		}
	}

	return count
}

func dfs(x, y int, grid [][]int) {
	if x < 0 || x >= len(grid) || y < 0 || y >= len(grid[0]) {
		return
	}

	if grid[x][y] == 1 {
		return
	}

	grid[x][y] = 1

	dfs(x, y+1, grid)
	dfs(x+1, y, grid)
	dfs(x, y-1, grid)
	dfs(x-1, y, grid)
}

func main() {
	//strings := []string{"/\\", "\\/"}
	strings := []string{"//", "/ "}
	slashes := regionsBySlashes(strings)

	fmt.Println(slashes)
}
