PROGRAM openacc_present
  CALL clawloop (1,2,3,4)
END PROGRAM openacc_present

!$claw data all-present
SUBROUTINE clawloop (i,j,k,l)
  INTEGER :: i,j,k,l
END SUBROUTINE clawloop
