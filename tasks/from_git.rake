
# installs dependencies for the pelican project
# given that they are hosted in public git repositories
# and use buildr as a build tool

def from_git url
  old = Dir.getwd
  dir = project('pelican')._(:target, File.basename(url))
  system "git clone --depth=1 #{url} #{dir}"
  rm_rf "#{dir}/.git"
  Dir.chdir(dir)
  system "buildr install"
  Dir.chdir(old)
  rm_rf "#{dir}"
end
