# maven dependencies
LOMBOK      = 'org.projectlombok:lombok:jar:0.11.6'
JUNIT4      = 'junit:junit:jar:4.10'
JPARSEC     = 'jparsec:jparsec:jar:2.0.1'
GUAVA       = 'com.google.guava:guava:jar:13.0.1'
SNAKE_YAML  = 'org.yaml:snakeyaml:jar:1.8'
SLF4J       = struct(
  :api     => 'org.slf4j:slf4j-api:jar:1.7.3',
  :simple  => 'org.slf4j:slf4j-simple:jar:1.7.3')

define 'pipeline' do
  project.version = '2.0.0'
  
  define 'api' do
    eclipse.natures :java
    compile.with LOMBOK
    package :jar
  end
  
  define 'pelican' do
    eclipse.natures :java
    
    compile.options.source = '1.5'
    compile.options.target = '1.5'
    
    # project components
    define 'api' do
      compile.with LOMBOK,GUAVA,
        projects('pipeline:api','pipeline:util'),
        projects('lexicon','lambdacalc','predcalc','settings')
      test.with LOMBOK,JPARSEC,GUAVA,SNAKE_YAML,SLF4J
      package :jar
    end
    define 'lexicon' do
      compile.with LOMBOK, GUAVA,
        projects('lambdacalc','settings')
      package :jar
    end
    define 'lambdacalc' do
      compile.with LOMBOK, GUAVA, JPARSEC
      package :jar
    end
    define 'predcalc' do
      compile.with LOMBOK, SLF4J,
        projects('lambdacalc','settings')
      package :jar
    end
    define 'settings' do
      compile.with LOMBOK, SNAKE_YAML
      package :jar
    end
  end
  
  define 'util' do
    compile.with LOMBOK, GUAVA,
      projects('pipeline:api')
    package :jar
  end
end
