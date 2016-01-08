# Installation of the CLAW Fortran compiler

### Dependencies

CLAW Fortran compiler (clawfc) is built on the top of the
[OMNI Compiler](http://www,omni-compiler.org). It is currently tested with
version
[0.9.2](http://omni-compiler.org/download/stable/omnicompiler-0.9.2.tar.bz2).


To build the and install the CLAW Fortran Compiler, use the followings commands.
By default, the `OMNI_HOME` variable is set up to `/usr/local/`. If your
installation of OMNI Compiler is pointing to the default directory, the option
is not necessary.

```bash
cmake -DOMNI_HOME=<omni compiler install dir> .
make
make install
```

#### Test your installation with an example
##### Source code
File: `simple_sample.f90`
```Fortran
PROGRAM simple_sample
  CALL my_simple_subroutine
END PROGRAM simple_sample

SUBROUTINE my_simple_subroutine
  INTEGER :: i
  !$claw loop-fusion
  DO i=1,2
    PRINT *, 'First loop body:',i
  END DO

  !$claw loop-fusion
  DO i=1,2
    PRINT *, 'Second loop body:',i
  END DO

  !$claw loop-fusion
  DO i=1,2
    PRINT *, 'Third loop body:',i
  END DO
END
```

##### Compilation
Compile the original source code to compare the output
```bash
gfortran -o simple_sample1 simple_sample1.f90
```

Apply code transformation and compile the transformed source file
```bash
clawfc -o transformed_code.f90 simple_sample.f90  # Generate transformed_code
gfortran -o simple_sample2 transformed_code.f90   # Compile with std compiler
```

##### simple_sample1's output:
```bash
$ ./simple_sample1
First loop body:           1
First loop body:           2
Second loop body:          1
Second loop body:          2
Third loop body:           1
Third loop body:           2
```

##### simple_sample2's output:
```bash
$ ./simple_sample2
First loop body:           1
Second loop body:          1
Third loop body:           1
First loop body:           2
Second loop body:          2
Third loop body:           2
```