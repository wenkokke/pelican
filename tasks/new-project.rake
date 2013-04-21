
# scaffold: create new project
task 'new-project', :name do |t, args|
  project = args[:name]
  mkdir_p "#{project}"
  mkdir_p "#{project}/src/main/java"
  mkdir_p "#{project}/src/test/java"
end