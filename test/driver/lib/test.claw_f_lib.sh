#!/bin/bash

# source the library
. ../../../driver/libexec/claw_f_lib.sh.in

# Test the claw_f_norm_file_name function
function test_claw_f_norm_file_name()
{
  # Array of input values
  test_inputs=()
  test_inputs+=("./hoge/fuga-a.f90")
  test_inputs+=("fuga-a.f90")
  test_inputs+=("original_code.f90")
  test_inputs+=("original_code.F90")
  test_inputs+=("original_code.f")
  test_inputs+=("original_code.F")

  # Array of expected output value
  expected_outputs=()
  expected_outputs+=("hoge_2f_fuga_2d_a")
  expected_outputs+=("fuga_2d_a")
  expected_outputs+=("original_5f_code")
  expected_outputs+=("original_5f_code")
  expected_outputs+=("original_5f_code")
  expected_outputs+=("original_5f_code")

  for ((i=0;i<${#test_inputs[@]};++i))
  do
    test_result=$(claw_f_norm_file_name "${test_inputs[i]}")
    if [ "$test_result" != "${expected_outputs[i]}" ]
    then
      echo "Error: claw_f_norm_file_name: $test_result != ${expected_outputs[i]}"
      exit 1
    fi
  done
}


# Execute test cases
test_claw_f_norm_file_name
