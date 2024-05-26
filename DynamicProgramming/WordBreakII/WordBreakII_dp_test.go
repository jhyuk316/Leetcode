//go:build WordBreakII_dp

package WordBreakII

import (
	"fmt"
	"testing"
)

func Test_wordBreak(t *testing.T) {
	fmt.Println(wordBreak("catsanddog", []string{"cat", "cats", "and", "sand", "dog"}))
}
