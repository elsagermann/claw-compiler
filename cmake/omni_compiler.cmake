if(__omni_compiler)
	return()
endif()
set(__omni_compiler YES)

function(omni_generate_xmod)
  set(oneValueArgs TARGET SOURCE)
  cmake_parse_arguments(omni_generate_xmod "" "${oneValueArgs}" "" ${ARGN} )

  if(NOT EXISTS ${CMAKE_CURRENT_SOURCE_DIR}/${omni_generate_xmod_SOURCE})
    message(FATAL "Input file ${CMAKE_CURRENT_SOURCE_DIR}/${omni_generate_xmod_SOURCE} does not exists !")
  endif()

  add_custom_target(${omni_generate_xmod_TARGET} ALL)

  set(FPP_ARG_LIST ${FPPFLAGS})
  separate_arguments(FPP_ARG_LIST)

  if("${CMAKE_Fortran_COMPILER_ID}" MATCHES "Cray")
    string(REGEX REPLACE "\\.[^.]*$" "" CRAY_PP_OUTPUT ${omni_generate_xmod_SOURCE})
    add_custom_command(
      TARGET ${omni_generate_xmod_TARGET}
      COMMAND ${CMAKE_Fortran_COMPILER} ${FPP_ARG_LIST}
        ${CMAKE_CURRENT_SOURCE_DIR}/${omni_generate_xmod_SOURCE}
      COMMAND
        ${OMNI_F_FRONT} -M${CMAKE_CURRENT_BINARY_DIR}
        ${CMAKE_CURRENT_BINARY_DIR}/"${CRAY_PP_OUTPUT}.i"
      DEPENDS ${CMAKE_CURRENT_SOURCE_DIR}/${omni_generate_xmod_SOURCE}
      COMMENT "Generating .xmod file for ${omni_generate_xmod_SOURCE}"
    )
  else()
    add_custom_command(
      TARGET ${omni_generate_xmod_TARGET}
      COMMAND ${CMAKE_Fortran_COMPILER} ${FPP_ARG_LIST}
        ${CMAKE_CURRENT_SOURCE_DIR}/${omni_generate_xmod_SOURCE} |
        ${OMNI_F_FRONT} > /dev/null
      DEPENDS ${CMAKE_CURRENT_SOURCE_DIR}/${omni_generate_xmod_SOURCE}
      COMMENT "Generating .xmod file for ${omni_generate_xmod_SOURCE}"
    )
  endif()
endfunction()
