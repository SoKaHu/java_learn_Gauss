
public class GrausGauss extends MiniJava {

  private static int lines;

  public static int[] readMatrix() {
    int n = (lines * lines) + lines;
    int[] matrix = new int[n];
    for (int i = 0; i < n; i++) {
      int x = readInt("Bitte geben Sie Ihre " + (i + 1) + ". Zahl ein:");
      matrix[i] = x;
    }
    return matrix;
  }

  public static void printMatrix(int[] matrix) {
    int n = (lines * lines) + lines;
    int cut = n / lines;
    int help = 1;
    writeConsole("{ " + matrix[0]);
    for (int i = 1; i < n; i++) {
      help += 1;
      if (help != cut) {
        writeConsole(" " + matrix[i]);
      }
      if (help == cut && i < n - 1) {
        writeConsole(" | " + matrix[i] + " }\n");
        help = 0;
        writeConsole("{");
      }
      if (help == cut && i == n - 1) {
        writeConsole(" | " + matrix[i] + " }");
      }
    }
  }

  public static int get(int[] matrix, int line, int column) {
    int n = (lines * lines) + lines;
    int cut = n / lines;
    int get = matrix[0];
    int whichline = 1;
    int whichrow = 0;
    for (int i = 0; i < n; i++) {
      whichrow += 1;
      if (whichline == line) {
        if (whichrow == column) {
          get = matrix[i];
          break;
        }
      }
      if (whichrow == cut) {
        whichline += 1;
        whichrow = 0;
      }
    }
    writeConsole(get);
    return (get);
  }


  public static void set(int[] matrix, int line, int column, int value) {
    int n = (lines * lines) + lines;
    int cut = n / lines;
    int whichline = 1;
    int whichrow = 0;
    for (int i = 0; i < n; i++) {
      whichrow += 1;
      if (whichline == line) {
        if (whichrow == column) {
          matrix[i] = value;
          break;
        }
      }
      if (whichrow == cut) {
        whichline += 1;
        whichrow = 0;
      }
    }
  }


  public static void multLine(int[] matrix, int line, int factor) {
    int n = (lines * lines) + lines;
    int cut = n / lines;
    int whichline = 1;
    int whichrow = 0;
    for (int i = 0; i < n; i++) {
      whichrow += 1;
      if (whichline == line) {
        matrix[i] = matrix[i] * factor;
      }
      if (whichrow == cut) {
        whichline += 1;
        whichrow = 0;
      }
    }
  }



  public static void multAddLine(int[] matrix, int line, int otherline, int factor) {
    multLine(matrix, otherline, factor);
    int n = (lines * lines) + lines;
    int cut = n / lines;
    int whichline = 1;
    int whichrow = 0;
    int y = otherline * cut - cut;
    for (int i = 0; i < n; i++) {
      whichrow += 1;
      if (whichline == line) {
        matrix[i] = matrix[i] + matrix[y];
        y += 1;
      }
      if (whichrow == cut) {
        whichline += 1;
        whichrow = 0;
      }
    }
  }

  public static void swap(int[] matrix, int lineA, int lineB) {
    int n = (lines * lines) + lines;
    int cut = n / lines;
    int whichline = 1;
    int whichrow = 0;
    int y = lineB * cut - cut;
    int z;
    for (int i = 0; i < n; i++) {
      whichrow += 1;
      if (whichline == lineA) {
        z = matrix[i];
        matrix[i] = matrix[y];
        matrix[y] = z;
        y += 1;
      }
      if (whichrow == cut) {
        whichline += 1;
        whichrow = 0;
      }
    }
  }

  public static void searchSwap(int[] matrix, int fromLine) {
    int n = (lines * lines) + lines;
    int cut = n / lines;
    int whichline = 1;
    int whichrow = 0;
    int x = 0;
    for (int i = 0; i < n; i++) {
      whichrow += 1;
      if (whichline == fromLine) {
        if (matrix[x] == 0) {
          swap(matrix, fromLine, fromLine + 1);
        }
      }
      if (whichrow == cut) {
        whichline += 1;
        whichrow = 0;
        x += cut + 1;
      }
    }
  }

  public static int kgv(int a, int b) {
    int x = 1;
    int kgv = a;
    if (a > b) {
      while (kgv % b != 0) {
        kgv = a * x;
        x += 1;
      }
    }
    if (b > a) {
      kgv = b;
      while (kgv % a != 0) {
        kgv = b * x;
        x += 1;
      }
    }
    return kgv;
  }

  public static int[] rowEchelonToResult(int[] matrix) {
    int n = (lines * lines) + lines;
    int cut = n / lines;
    int lastline = lines;
    int lastcolumn = cut - 1;
    int[] vector = new int[lines];
    int x = 1;
    int y = 2;
    int whichline = 1;
    int whichrow = 0;
    int z;
    for (int i = 0; i < n; i++) {
      whichrow += 1;
      if (whichline == lastline || lastline > 1) {
        z = matrix[i + lastcolumn];
        while (matrix[i + lastcolumn - y] != 0) {
          x = 1;
          z = z - (matrix[i + lastcolumn - y + 1] * vector[lines - x]);
          y += 1;
          x += 1;
        }
        if (matrix[i + lastcolumn - y] == 0) {
          vector[lines - x] = z / matrix[i + lastcolumn - y + 1];
          lastline -= 1;
          i = 0;
          y = 2;
        }
      }
      if (whichline == lastline || lastline == 1) {
        z = matrix[i + lastcolumn];
        while (matrix[i + lastcolumn - y] != 0 || i + lastcolumn - y >= 1) {
          x = 1;
          z = z - (matrix[i + lastcolumn - y + 1] * vector[lines - x]);
          y += 1;
          x += 1;
        }
        if (matrix[i + lastcolumn - y] == 0 || i + lastcolumn - y == 0) {
          z = z - (matrix[i + lastcolumn - y + 1] * vector[lines - x]);
          x += 1;
          vector[lines - x] = z / matrix[i + lastcolumn - y];
          break;
        }
      }
      if (whichrow == cut) {
        whichline += 1;
        whichrow = 0;
      }
    }
    return (vector);
  }

  // methode solvesystem



  public static void main(String[] args) {
    lines = read("Geben Sie die Anzahl der Gleichungen ein.");
    while (lines <= 0) {
      lines = read("Es müssen mehr als 0 Gleichungen sein!");
    }
    int[] GLS = readMatrix();
    // Gleichungssystem lösen mit solve
    // printMatrix(vektor)



  }
}
