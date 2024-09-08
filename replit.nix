{ pkgs }: {
  deps = [
    pkgs.run
    pkgs.unzip
    pkgs.graalvm17-ce
    pkgs.maven
    pkgs.replitPackages.jdt-language-server
    pkgs.replitPackages.java-debug
    pkgs.xorg.libX11
    pkgs.gitAndTools.git-lfs
  ];
  env = {
    LD_LIBRARY_PATH = pkgs.lib.makeLibraryPath [
      pkgs.xorg.libX11
      pkgs.xorg.libXxf86vm
      pkgs.libGL
      pkgs.xorg.libXtst
    ];
  };
}