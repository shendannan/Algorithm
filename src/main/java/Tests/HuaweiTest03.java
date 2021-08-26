package Tests;

import java.util.*;

/**
 * 华为2021.8.25笔试题3，分值300
 * 任务执行时间计算
 * 有若干个任务，每个任务有其依赖的其他任务，以及执行时间（秒）
 * 当A依赖B，C时，必须等B和C都执行完才能执行A
 * 所有没有依赖的任务可以并行执行
 * 问最少多少秒可以执行完所有任务，如果不能执行所有任务，返回-1
 *
 * 示例输入：
 * 3        //代表有3个任务（下标从0开始）
 * -1 1     //代表第0个任务不依赖任务任务，执行耗时1秒
 * 2 2      //代表第1个任务依赖第2个任务，执行耗时2秒
 * 0,1 3    //代表第2个任务依赖第0个和第1个任务，执行耗时3秒
 *
 * 输出-1，因为出现循环依赖。
 *
 * 解题思路：模拟
 */
public class HuaweiTest03 {
    static class Job {
        public int id;
        public boolean flag; //代表该任务是否完成，默认false为未完成
        public List<Integer> dependIds;
        public int time;

        public Job(int idd, int t) {
            time = t;
            id = idd;
            dependIds = new ArrayList<>();
            flag = false;
        }

        public void setDependIds(List<Integer> list) {
            dependIds.addAll(list);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); //任务个数
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> cur = new ArrayList<>();
            String temp = in.next();
            String[] temps = temp.split(",");
            for (String id : temps) {
                cur.add(Integer.valueOf(id));
            }
            Job job = new Job(i, in.nextInt());
            job.setDependIds(cur);
            jobs.add(job);
        }
        boolean error;
        int totalTime = 0;
        // 每一秒算一次
        while(true){
            totalTime++;
            // 可执行任务组
            List<Job> need = findAvailable(jobs);
            // 没有可执行任务组，结束循环
            if (need.size() == 0) {
                //如果所有任务都执行了，则未出错，结束
                error = !isAllJobDone(jobs);
                break;
            }
            for (Job task : need) {
                task.time--; //由于过了1秒，当前任务所需时间减少1秒
                if (task.time == 0) {//如果当前任务剩余所需时间为0，说明这个任务完成了
                    task.flag = true;
                    //将其他job中需要依赖task这个任务的，其依赖设置为-1（不再依赖），因为task已经完成了
                    for (Job job : jobs) {
                        if (!job.flag) {
                            for (int j = 0; j < job.dependIds.size(); j++) {
                                if (job.dependIds.get(j) == task.id) {
                                    job.dependIds.set(j,-1);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(error?-1:totalTime);
    }

    //检查是否每个任务都完成了
    private static boolean isAllJobDone(List<Job> tasks) {
        int i = 0;
        for (; i < tasks.size(); i++) {
            if (!tasks.get(i).flag) {
                break;
            }
        }
        return i == tasks.size();
    }

    //找到未执行的job中，所有不依赖其他任务的job
    private static List<Job> findAvailable(List<Job> tasks) {
        List<Job> need = new ArrayList<>();
        for (Job task : tasks) {
            if (!task.flag) {
                int j = 0;
                for (; j < task.dependIds.size(); j++) {
                    if (task.dependIds.get(j) != -1) {
                        break;
                    }
                }
                if (j == task.dependIds.size()) {
                    need.add(task);
                }
            }
        }
        return need;
    }
}
