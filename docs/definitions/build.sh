
set -euo pipefail

pdflatex -interaction=batchmode *.tex
#echo "First pdflatex run errcode $?"
pdflatex -interaction=batchmode *.tex
#echo "Second pdflatex run errcode $?"

rm *.aux  *.log  *.out
